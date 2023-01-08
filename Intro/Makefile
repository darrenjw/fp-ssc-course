# Makefile


TARGETS=Readme.pdf

FORCE:
	make $(TARGETS)


%.pdf: %.md
	pandoc $< -t beamer -o $@


view: $(TARGETS)
	xdg-open $(TARGETS) &

edit:
	emacs Makefile *.md &

clean:
	rm -f *~ *.pdf


# eof

