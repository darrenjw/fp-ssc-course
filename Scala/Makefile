# Makefile

FORCE:
	make doc

doc:
	sbt mdoc
	cd md ; make

edit:
	emacs Makefile build.sbt *.md docs/*.md src/main/scala/*.scala &



# eof


