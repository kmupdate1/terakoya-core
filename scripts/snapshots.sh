#!/bin/bash
# 寺子屋ラボ Snapshots 出荷スクリプト

echo "🚀 [Snapshots] 探求の成果を Nexus へ配送中..."

# 環境変数の存在チェック（防衛策）
if [ -z "$NEXUS_USERNAME" ] || [ -z "$NEXUS_PASSWORD" ]; then
    echo "❌ Error: 環境変数 NEXUS_USERNAME または NEXUS_PASSWORD が設定されていません。"
    echo "代表の MBP2018 で export されているか確認してください。"
    exit 1
fi

./gradlew publish

if [ $? -eq 0 ]; then
    echo "✅ Snapshots の配送に成功しました。"
else
    echo "❌ 配送に失敗しました。ネットワークまたは権限を確認してください。"
fi
