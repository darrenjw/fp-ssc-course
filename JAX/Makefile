# Makefile

TARGETS=Readme.md Random.md Example.md

FORCE:
	make $(TARGETS)

%.md: %.qmd Makefile
	quarto render $< --to gfm

edit:
	emacs Makefile *.qmd *.py &


clean:
	rm -f *~ $(TARGETS)


# eof


