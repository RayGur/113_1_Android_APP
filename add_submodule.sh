#!/bin/bash

# 檢查參數是否提供
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <repo_url> <path>"
    exit 1
fi

# 避免重複添加子模組
if [ -d "$SUBMODULE_PATH" ]; then
    echo "Submodule at $SUBMODULE_PATH already exists. Skipping..."
    exit 0
fi

REPO_URL=$1
SUBMODULE_PATH=$2

echo "Adding submodule: $REPO_URL at $SUBMODULE_PATH"
git submodule add $REPO_URL $SUBMODULE_PATH

# 初始化並更新子模組
echo "Initializing and updating submodule..."
git submodule update --init --recursive

echo "Submodule added successfully!"
#!/bin/bash

# 檢查列表文件是否存在
LIST_FILE="submodule_list.txt"
if [ ! -f $LIST_FILE ]; then
    echo "Error: $LIST_FILE not found!"
    exit 1
fi

# 遍歷文件中的每行
while IFS=" " read -r REPO_URL SUBMODULE_PATH; do
    echo "Adding submodule: $REPO_URL at $SUBMODULE_PATH"
    git submodule add $REPO_URL $SUBMODULE_PATH

    # 初始化並更新子模組
    echo "Initializing and updating submodule..."
    git submodule update --init --recursive
done < $LIST_FILE

echo "All submodules added successfully!"

