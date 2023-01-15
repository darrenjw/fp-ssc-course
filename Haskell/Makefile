# Makefile


FORCE:
	stack clean
	make build
	make run


build:
	stack build

run:
	stack exec ml-ga-exe

edit:
	emacs Makefile *.yaml lib/*.hs app/*.hs &


# eof


