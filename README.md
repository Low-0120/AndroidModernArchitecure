# AndroidModernArchitecure
依存関係グラフ
プロジェクト間依存の統合管理をコンポジットビルドにて実現
依存関係グラフ
![dep_grah Image](docs/images/modernarchitercture%20dep_grah.png)

# App
Androidアプリケーションの全ての基盤になるレイヤー
## Auth
Firebase匿名ログインをアプリ起動時に実現
Activity起動時Token差し替え
## Singlton
内部依存関係をDaggerHilt2にて外部コンテナに定義
各モジュール間において一元データアクセスが可能

# Core
プロジェクトシステム設定/プロダクト設定
## common
プロジェクトシステムにおいて、普遍的な設定値
## model
プロダクトにおける設定値
## data
プロダクト設定におけるデータ管理
## database
プロダクト設定におけるローラルデータベース管理
## datastore
プロダクト設定におけるパッケージに内包するデータ管理
## designsystem
プロダクト設定におけるローカルリソース管理
## domain
プロダクトにおけるビジネスロジックの定義
## network
外部通信設定
## ui
プロダクトにおける共通UIの定義
## testing
テストに関連する設定/定義


# Features
ユーザーインタラクションにまつわるレイヤー


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
