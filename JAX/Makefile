# Makefile

TARGETS=Readme.md Random.md Example.md

FORCE:
	make $(TARGETS)

%.md: %.qmd
	quarto render $< --to md

edit:
	emacs Makefile *.qmd *.py &



# eof


