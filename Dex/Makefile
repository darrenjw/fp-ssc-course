# Makefile

LIBPATH=BUILTIN_LIBRARIES:.

FORCE:
	make djwutils.html
	make DexCC.html
	cp *.html ../docs/
	make ml-ga

%: %.dx djwutils.dx
	time dex --lib-path $(LIBPATH) -O script $<

%.html: %.dx djwutils.dx
	dex --lib-path $(LIBPATH) -O script $< --outfmt html > $@

edit:
	emacs Makefile *.md *.dx &

clean:
	rm -f *.out *.tsv *~ *.html *.pdf

repl:
	dex --lib-path $(LIBPATH) repl

.PRECIOUS: %.html
%: %.html
	make $<
	xdg-open $<



# eof

