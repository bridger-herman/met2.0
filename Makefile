SRC_DIR = src
BIN_DIR = bin

SRCS = $(wildcard $(SRC_DIR)/*.java)
BINS = $(addprefix $(BIN_DIR)/,  $(notdir $(SRCS:.java=.class)))
MAIN = Driver

all: $(MAIN)

$(MAIN): $(BIN_DIR) $(BINS)
	@cd $(BIN_DIR); java $(MAIN)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	javac -d $(BIN_DIR) $(SRCS)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

clean:
	rm -rf bin

rebuild: clean all
