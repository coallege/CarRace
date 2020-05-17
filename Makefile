run: build
	java Test

build:
	$(foreach javafile,$(wildcard *.java),javac $(javafile);)

.PHONY: build run
