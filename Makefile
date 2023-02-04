# Makefile

FORCE:
	cd Intro; make
	cd Scala; make
	cd Haskell; make
	#cd JAX; make
	cd Dex; make

edit:
	emacs Makefile *.md Intro/*.md & 



# eof


