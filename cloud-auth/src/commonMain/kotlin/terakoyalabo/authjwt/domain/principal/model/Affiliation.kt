package terakoyalabo.authjwt.domain.principal.model

enum class Affiliation {
    STAFF,    // 本部長・運営（フルアクセス）
    GUEST,    // カフェやゲストハウスの利用者
    DRONE,    // ミツバチ監視カメラやセンサー、Pi5などのデバイス
    MASTER    // システムの根源的な保守用（もし必要なら）
}
