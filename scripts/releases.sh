#!/bin/bash
# 寺子屋ラボ Releases 出荷スクリプト (正式版・高セキュリティ)
export LANG=ja_JP.UTF-8

# 🏛️ [理] スクリプトの場所に関わらず、プロジェクトルートに移動する
cd "$(dirname "$0")/.."

echo "🏛️ [Location] 現在地をプロジェクトルート $(pwd) に固定しました。"
echo "🏛️ [Releases] 正式な理を Nexus へ結晶化中..."

# 1. 環境変数の存在チェック
if [ -z "$NEXUS_USERNAME" ] || [ -z "$NEXUS_PASSWORD" ]; then
    echo "❌ Error: 環境変数が必要です。"
    exit 1
fi

# 2. 実行確認
echo "⚠️  警告: これは Snapshot ではなく【正式リリース】です。"
# shellcheck disable=SC2162
read -p "本当に実行する場合は大文字で 'YES' と入力してください: " CONFIRM

if [ "$CONFIRM" != "YES" ]; then
    echo "🛑 意志が確認できませんでした。リリースを中止します。"
    exit 0
fi

# 🌟 魂の刻印（リリースコメントの入力）
echo "💬 このリリースに冠する『理』の言葉を入力してください（例: 概念の浄化と防衛線の確立）"
# shellcheck disable=SC2162
read -p "Comment: " RELEASE_COMMENT
if [ -z "$RELEASE_COMMENT" ]; then
    RELEASE_COMMENT="定期的な概念の結晶化"
fi

# 3. 変数の準備
VERSION=$(grep 'library.version' gradle.properties | cut -d'=' -f2 | tr -d '[:space:]')
TAG_NAME="v$VERSION"
CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)

echo "📦 [History] 正史(main)への統合を開始します..."

# 4. 歴史の固定 (Fail-fast)
set -e
git checkout main
git pull origin main
git merge "$CURRENT_BRANCH" --no-ff -m "Release $TAG_NAME: $RELEASE_COMMENT"

# 5. 歴史の刻印 (Tag)
if git rev-parse "$TAG_NAME" >/dev/null 2>&1; then
    echo "⚠️  $TAG_NAME は既に歴史に存在します。タグ打ちはスキップします。"
else
    echo "🏷️  $TAG_NAME を歴史に刻印中..."
    git tag -a "$TAG_NAME" -m "$RELEASE_COMMENT"
    git push origin main --tags
fi

# 6. Nexusへの放流
echo "🚀 承認されました。出荷を開始します..."
./gradlew publish -Prelease=true

# 7. 成功後の「自動代謝」プロセス
echo "✨ 正式版 $TAG_NAME の出荷に成功しました。"
echo "📝 次のバージョンへ移行中..."

# バージョンインクリメント & 時刻の精緻な打刻
NEXT_VERSION=$(echo "$VERSION" | awk -F. '{$NF = $NF + 1;} 1' | sed 's/ /./g')
NOW=$(date "+%Y/%m/%d %H:%M:%S")

# gradle.properties の書き換えと履歴追記
sed -i '' "s/library.version=$VERSION/library.version=$NEXT_VERSION/" gradle.properties
sed -i '' "/library.version=$NEXT_VERSION/a\\
# $NOW [$VERSION] $RELEASE_COMMENT
" gradle.properties

# 変更の保存と正史への反映
git add gradle.properties
git commit -m "Chore: $TAG_NAME 出荷完了 ($NOW)。Next -> $NEXT_VERSION"
git push origin main

# 8. 現場（develop）へ同期しながら帰還
echo "🔄 開発ブランチ ($CURRENT_BRANCH) を最新の理に同期中..."
git checkout "$CURRENT_BRANCH"
git merge main

echo "✅ 全行程が完了しました。"
echo "🔥 次の『千本ノック』の準備が整いました（Next: $NEXT_VERSION）"
