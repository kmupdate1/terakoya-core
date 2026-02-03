#!/bin/bash
# 寺子屋ラボ Releases 出荷スクリプト (正式版・高セキュリティ)

echo "🏛️ [Releases] 正式な理を Nexus へ結晶化中..."

# 環境変数の存在チェック
if [ -z "$NEXUS_USERNAME" ] || [ -z "$NEXUS_PASSWORD" ]; then
    echo "❌ Error: 環境変数が必要です。"
    exit 1
fi

# 実行確認（大文字 YES のみを許可）
echo "⚠️  警告: これは Snapshot ではなく【正式リリース】です。"
# shellcheck disable=SC2162
read -p "本当に実行する場合は大文字で 'YES' と入力してください: " CONFIRM

if [ "$CONFIRM" != "YES" ]; then
    echo "🛑 意志が確認できませんでした（入力: $CONFIRM）。リリースを中止します。"
    exit 0
fi

echo "🚀 承認されました。出荷を開始します..."

# -Prelease=true を付与して実行
./gradlew publish -Prelease=true

if [ $? -eq 0 ]; then
    VERSION=$(grep 'library.version' gradle.properties | cut -d'=' -f2)
    echo "✨ 正式版 v$VERSION の出荷が完了しました。歴史が刻まれました。"
else
    echo "❌ 正式版の出荷に失敗しました。ログを確認してください。"
fi
