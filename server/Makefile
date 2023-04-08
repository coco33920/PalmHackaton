BUILD = target
SERVER = server.jar

# Colors
ifneq ($(shell tput colors),0)
    C_BOLD=\e[1m
    C_RESET=\033[00m
    C_BLUE=\e[34m
    C_RED=\e[31m
    C_CYAN=\e[36m
else
    C_BOLD=
    C_RESET=
    C_BLUE=
    C_CYAN=
endif

# Definitions
_ECHO  = /bin/echo -e

OK = @ $(_ECHO) "$(C_BOLD)[$(C_BLUE)OK$(C_RESET)$(C_BOLD)]$(C_RESET)"
KO = @ $(_ECHO) "$(C_BOLD)[$(C_RED)KO$(C_RESET)$(C_BOLD)]$(C_RESET)"
NTD = @ $(_ECHO) "$(C_BOLD)Nothing to do.$(C_RESET)"

# Errors
DIE = exit 1

all: $(SERVER)
	$(NTD)

%:
	$(KO) "Unknown directive $@" && $(DIE)

$(SERVER): $(BUILD)
	@ find $(BUILD) -name '*-with-dependencies.jar' -exec cp {} $(SERVER) \;
	$(OK) "Created server jar"

$(BUILD):
	@ mvn clean install
	$(OK) "Compiled server"

run: $(SERVER)
	@ java -jar $(SERVER)
	$(OK) "Started server"

.PHONY: run

clean:
	@ mvn clean
	$(OK) "Removed $(C_CYAN)target$(RESET)"

fclean: clean
	@ rm -rf $(SERVER)
	$(OK) "Removed $(C_CYAN)server.jar$(RESET)"
	$(NTD)

re: fclean all

.PHONY: clean fclean re
