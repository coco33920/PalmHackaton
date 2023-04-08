SERVER = server
CLIENT = front

JAR = $(SERVER)/server.jar
DIST = $(CLIENT)/dist

all: $(DIST) $(JAR)


$(DIST):
	@ $(MAKE) -sC $(CLIENT)

$(JAR):
	@ $(MAKE) -sC $(SERVER)

run:
	@+ $(MAKE) -sC $(CLIENT) run &
	@ $(MAKE) -sC $(SERVER) run

.PHONY: run

clean:
	@ $(MAKE) -sC $(CLIENT) clean
	@ $(MAKE) -sC $(SERVER) clean

fclean:
	@ $(MAKE) -sC $(CLIENT) fclean
	@ $(MAKE) -sC $(SERVER) fclean

re: fclean all

.PHONY: clean fclean re

