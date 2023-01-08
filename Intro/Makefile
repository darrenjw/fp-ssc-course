# Makefile


TARGETS=Readme.pdf Example.pdf

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

