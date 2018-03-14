# Makefile
.PHONY: help deps

SHELL       := /bin/bash
export PATH := bin:$(PATH)

help:
	@echo "Usage: make {deps|help}" 1>&2 && false
    
bin/boot:
	mkdir -p bin/                                                                              && \
	curl -fsSLo bin/boot https://github.com/boot-clj/boot-bin/releases/download/latest/boot.sh && \
	chmod 755 bin/boot

deps: bin/boot