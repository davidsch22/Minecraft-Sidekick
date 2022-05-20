import os
import math
import re
import string
import nltk
import pandas as pd
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer
from textblob import TextBlob
from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

nltk.download('punkt')

categories = ["blocks", "items", "mobs", "places", "gameplay"]
stop_words = set(stopwords.words('english'))
stop_words = stop_words.union(set(string.punctuation))
porter = PorterStemmer()

model = SentenceTransformer('bert-base-nli-mean-tokens')


def tokenize_line(line: str) -> list:
    tokens = nltk.word_tokenize(line)
    stemmed = []
    for token in tokens:
        if token.lower() not in stop_words and re.search('[A-Za-z]', token) != None:
            stemmed.append(porter.stem(token))
    return stemmed


def preprocess_kd():
    for category in categories:
        tf_idf = pd.DataFrame(dtype='float64')
        tf = pd.DataFrame(dtype='float64')
        idf = pd.DataFrame(dtype='float64')

        for (dirPath, dirNames, fileNames) in os.walk('KnowledgeDatabase/' + category):
            for fileName in fileNames:
                filePath = dirPath + "/" + fileName
                print(filePath[18:])
                doc = pd.read_csv(filePath, sep='`', names=['Line'], encoding='utf-8')
                doc = doc.applymap(lambda x: tokenize_line(x))
                all_tokens = doc['Line'].explode()
                total = all_tokens.shape[0]
                unique_tokens = all_tokens.unique()
                for token in unique_tokens:
                    count = all_tokens[all_tokens == token].shape[0]
                    tf.loc[token, filePath[18:]] = count / total
        tf.fillna(0, inplace=True)
        N = tf.shape[1]
        idf = tf.apply(lambda row: math.log(N / (row[row > 0].shape[0]+1)), axis=1)
        tf_idf = tf.mul(idf, axis=0)
        tf_idf.to_csv('tf_idf_tables/tf-idf-' + category + '.csv')


def answer(category: str, q: str) -> str:    
    tf_idf = pd.DataFrame(dtype='float64')
    tokens = tokenize_line(q)

    if category == "blocks":
        tf_idf = pd.read_csv('tf_idf_tables/tf-idf-blocks.csv', index_col=[0])
    elif category == "items":
        tf_idf = pd.read_csv('tf_idf_tables/tf-idf-items.csv', index_col=[0])
    elif category == "mobs":
        tf_idf = pd.read_csv('tf_idf_tables/tf-idf-mobs.csv', index_col=[0])
    elif category == "places":
        tf_idf = pd.read_csv('tf_idf_tables/tf-idf-places.csv', index_col=[0])
    else: # gameplay
        tf_idf = pd.read_csv('tf_idf_tables/tf-idf-gameplay.csv', index_col=[0])

    good_tokens = tf_idf.index.intersection(tokens)
    best_docs = tf_idf.loc[good_tokens].sum(axis=0).nlargest(3).index.to_list()
    sentences = []
    # print(best_docs)
    for doc in best_docs:
        with open("KnowledgeDatabase/" + doc, encoding='utf-8') as file:
            doc_txt = file.readlines()
            doc_txt = "\n".join(doc_txt)
            blob = TextBlob(doc_txt)
            sentences.extend(blob.raw_sentences)

    sentence_embeddings = model.encode(sentences)
    q_vec = model.encode([q])
    similarities = cosine_similarity(q_vec, sentence_embeddings)
    return sentences[similarities.argmax()]


def main():
    preprocess_kd() # Uncomment to rebuild tf-idf tables
    
    # questions = pd.read_csv('QuestionsCorpus/AllQuestions.csv', sep=';')
    # totals = questions.iloc[0]
    # questions = questions.drop(0).reset_index(drop=True)

    # test_categories = ["Blocks", "Mobs", "Items"]
    # for cat in test_categories:
    #     print(cat)
    #     cat_tests = pd.DataFrame(columns=["Question", "Answer"])
    #     cat_tests["Question"] = questions[cat][questions[cat].notna()]
    #     cat_tests["Answer"] = cat_tests["Question"].apply(lambda x: answer(cat.lower(), x))
    #     cat_tests.to_csv('test_results/' + cat.lower() + '.csv')
    cat = input("What category is your question about? (Blocks, Items, Mobs, Places, Gameplay): ")
    while cat.lower() not in categories:
        cat = input("Category does not exist. Enter again. (Blocks, Items, Mobs, Places, Gameplay): ")
    q = input("Your question: ")
    print("Answer:", answer(cat.lower(), q))


if __name__ == "__main__":
    main()