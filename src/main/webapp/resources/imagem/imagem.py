#!/usr/bin/python
# encoding: utf-8
# filename: imagem.py

# Baixar imagem do CV Lattes para inserir no BD
#
# Desenvolvido por Igor Barreto Rodrigues - aluno da UFT
# email: igor.cientista@mail.uft.edu.br


import os
import sys
import urllib
import urlparse
from urllib2 import urlopen
from BeautifulSoup import BeautifulSoup as bs


class Imagem:

	def __init__(self):
		pass
		

	def baixarFoto(self,img_url,nome):
	
		pastaSaida = os.path.dirname(__file__) + "/" + nome + ".jpg"
		#print pastaSaida
		#diretorio = "%s%s" % (pastaSaida, nome+".jpg")
		imagemBaixada = file(pastaSaida, "wb")
		imagemWeb = urllib.urlopen(img_url)
		while True:
			buff = imagemWeb.read(65536)
			imagemBaixada.write(buff)
			if len(buff) == 0:
				break
		imagemBaixada.close()
		imagemWeb.close()

		return pastaSaida
