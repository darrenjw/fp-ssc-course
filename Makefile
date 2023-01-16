# Makefile

FORCE:
	cd Intro; make
	cd Scala; make

edit:
	emacs Makefile *.md Intro/*.md & 



# eof


