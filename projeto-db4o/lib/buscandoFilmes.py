import requests

titulo = str(input("Digite o nome do filme: "))

response = requests.get('http://www.omdbapi.com/?apikey=988c6f75&t=' + titulo)
jsonFilme = str(response.content)
novo = jsonFilme.replace('","',"','").split("','")
print(novo)
# for i in novo:
#     item = i.split('":"')
#     if(item[0].find("Title")):
#         print(item[1])

"""
"The Avengers"
2012
143
"https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
"8.0/10"
"""