# TaskManager - Windows環境構築手順書

## 目次
1. [概要](#概要)
2. [前提条件](#前提条件)
3. [必要なソフトウェア](#必要なソフトウェア)
4. [Java 21のインストール](#java-21のインストール)
5. [Mavenのインストール](#mavenのインストール)
6. [環境変数の設定](#環境変数の設定)
7. [インストールの確認](#インストールの確認)
8. [アプリケーションのセットアップ](#アプリケーションのセットアップ)
9. [トラブルシューティング](#トラブルシューティング)

---

## 概要

このドキュメントは、TaskManagerアプリケーションをWindows環境で動作させるために必要なJava開発環境の構築手順を説明します。

## 前提条件

- **OS**: Windows 10 / Windows 11（64bit版）
- **管理者権限**: インストール時に必要
- **インターネット接続**: ソフトウェアのダウンロードに必要
- **ディスク空き容量**: 最低2GB以上推奨

## 必要なソフトウェア

| ソフトウェア | バージョン | 用途 |
|------------|----------|------|
| Java Development Kit (JDK) | 21推奨（最低17以上） | Javaアプリケーションの実行環境 |
| Apache Maven | 3.9以上 | ビルドツール・依存関係管理 |
| Git | 最新版 | ソースコード管理（オプション） |


---

## Javaのインストール

**推奨バージョン: Java 21**（最低Java 17以上が必要）

以下のいずれかのJDKをインストールしてください。

### オプション1: IBM Semeru Runtimes

IBM Semeru Runtimesは、OpenJ9 JVMを使用した高性能なJava実行環境です。

1. IBM Semeru Runtimes公式サイトにアクセス
   - URL: https://developer.ibm.com/languages/java/semeru-runtimes/downloads/

2. **Java 21 (LTS)** を選択（または Java 17 (LTS)）

3. **Windows**セクションから以下をダウンロード
   - **推奨**: `Windows x64 JDK` (例: `ibm-semeru-open-jdk_x64_windows_21.x.x_openj9-x.x.x.msi`)
   - または: `Windows x64 JDK (zip)` (例: `ibm-semeru-open-jdk_x64_windows_21.x.x_openj9-x.x.x.zip`)

> **メリット**:
> - 無料でオープンソース
> - 商用利用可能
> - メモリ効率が良い
> - IBM のサポートあり

### オプション2: OpenJDK

OpenJDKは、Javaの公式オープンソース実装です。

1. OpenJDK公式サイトにアクセス
   - URL: https://jdk.java.net/21/ （または https://jdk.java.net/17/）

2. **Windows / x64**セクションから以下をダウンロード
   - `zip` ファイル (例: `openjdk-21.x.x_windows-x64_bin.zip`)

> **メリット**:
> - 完全無料
> - オープンソース
> - 商用利用可能
> - ライセンスの制約なし

### オプション3: Oracle JDK

Oracle JDKは、Oracle社が提供する商用サポート付きのJava実行環境です。

1. Oracle JDK公式サイトにアクセス
   - URL: https://www.oracle.com/java/technologies/downloads/#jdk21-windows （または #jdk17-windows）

2. **Windows**セクションから以下のいずれかをダウンロード
   - **推奨**: `x64 Installer` (例: `jdk-21_windows-x64_bin.exe`)
   - または: `x64 Compressed Archive` (例: `jdk-21_windows-x64_bin.zip`)

> **注意**:
> - Oracleアカウントでのログインが必要な場合があります
> - 商用利用には有償サポート契約が必要な場合があります

### 手順2: JDKのインストール

#### IBM Semeru Runtimesの場合

**MSI Installerの場合:**

1. ダウンロードした `.msi` ファイルをダブルクリックして実行

2. インストールウィザードが起動したら「Next」をクリック

3. ライセンス契約に同意して「Next」をクリック

4. インストール先の選択
   - デフォルト: `C:\Program Files\Semeru\jdk-21.x.x+x` （または `jdk-17.x.x+x`）
   - 必要に応じて変更可能（パスをメモしておく）
   - 「Next」をクリック

5. インストールが完了するまで待機

6. 「Finish」をクリックしてウィザードを終了

**ZIP Archiveの場合:**

1. ダウンロードした `.zip` ファイルを解凍

2. 解凍したフォルダを任意の場所に配置
   - 推奨: `C:\Program Files\Semeru\jdk-21` （または `jdk-17`）

3. フォルダのパスをメモしておく

#### OpenJDKの場合

1. ダウンロードした `.zip` ファイルを解凍

2. 解凍したフォルダを任意の場所に配置
   - 推奨: `C:\Program Files\Java\jdk-21` （または `jdk-17`）

3. フォルダのパスをメモしておく

#### Oracle JDKの場合

**EXE Installerの場合:**

1. ダウンロードした `.exe` ファイルをダブルクリックして実行

2. インストールウィザードが起動したら「次へ」をクリック

3. インストール先の選択
   - デフォルト: `C:\Program Files\Java\jdk-21` （または `jdk-17`）
   - 必要に応じて変更可能（パスをメモしておく）
   - 「次へ」をクリック

4. インストールが完了するまで待機

5. 「閉じる」をクリックしてウィザードを終了

**ZIP Archiveの場合:**

1. ダウンロードした `.zip` ファイルを解凍

2. 解凍したフォルダを任意の場所に配置
   - 推奨: `C:\Program Files\Java\jdk-21` （または `jdk-17`）

3. フォルダのパスをメモしておく


---

## Mavenのインストール

### 手順1: Mavenのダウンロード

1. Apache Maven公式サイトにアクセス
   - URL: https://maven.apache.org/download.cgi

2. **Binary zip archive**をダウンロード
   - 例: `apache-maven-3.9.6-bin.zip`
   - 最新の3.9.x以上のバージョンを選択

### 手順2: Mavenの配置

1. ダウンロードした `.zip` ファイルを解凍

2. 解凍したフォルダを任意の場所に配置
   - 推奨: `C:\Program Files\Apache\maven`
   - または: `C:\apache-maven-3.9.6`

3. フォルダのパスをメモしておく
   - 例: `C:\Program Files\Apache\maven\apache-maven-3.9.6`

> **注意**: パスにスペースが含まれていても問題ありませんが、スペースを含まないパスの方が推奨されます。


---

## 環境変数の設定

環境変数を設定することで、コマンドプロンプトやPowerShellからJavaとMavenを実行できるようになります。

### 手順1: システム環境変数の設定画面を開く

**Windows 10の場合:**
1. スタートメニューを右クリック
2. 「システム」を選択
3. 「システムの詳細設定」をクリック
4. 「環境変数」ボタンをクリック

**Windows 11の場合:**
1. スタートメニューを右クリック
2. 「システム」を選択
3. 「システムの詳細設定」をクリック
4. 「環境変数」ボタンをクリック

**または、どちらのバージョンでも:**
1. `Win + R` キーを押す
2. `sysdm.cpl` と入力してEnter
3. 「詳細設定」タブを選択
4. 「環境変数」ボタンをクリック

### 手順2: JAVA_HOMEの設定

1. 「システム環境変数」セクションで「新規」ボタンをクリック

2. 以下の情報を入力:
   - **変数名**: `JAVA_HOME`
   - **変数値**: JDKのインストールパス
     - IBM Semeru Runtimesの例: `C:\Program Files\Semeru\jdk-21.x.x+x` （または `jdk-17.x.x+x`）
     - OpenJDKの例: `C:\Program Files\Java\jdk-21` （または `jdk-17`）
     - Oracle JDKの例: `C:\Program Files\Java\jdk-21` （または `jdk-17`）

3. 「OK」をクリック

### 手順3: MAVEN_HOMEの設定

1. 「システム環境変数」セクションで「新規」ボタンをクリック

2. 以下の情報を入力:
   - **変数名**: `MAVEN_HOME`
   - **変数値**: Mavenのインストールパス
     - 例: `C:\Program Files\Apache\maven\apache-maven-3.9.6`

3. 「OK」をクリック

### 手順4: Pathへの追加

1. 「システム環境変数」セクションで「Path」を選択

2. 「編集」ボタンをクリック

3. 「新規」ボタンをクリックして、以下の2つのパスを追加:
   - `%JAVA_HOME%\bin`
   - `%MAVEN_HOME%\bin`

4. 「OK」をクリックして全てのダイアログを閉じる

> **重要**: 環境変数の変更を反映させるため、開いているコマンドプロンプトやPowerShellを全て閉じて、新しく開き直してください。


---

## インストールの確認

環境変数の設定が完了したら、正しくインストールされているか確認します。

### 手順1: コマンドプロンプトを開く

1. `Win + R` キーを押す
2. `cmd` と入力してEnter

または

1. スタートメニューで「cmd」と検索
2. 「コマンドプロンプト」を選択

### 手順2: Javaのバージョン確認

コマンドプロンプトで以下のコマンドを実行:

```cmd
java -version
```

**期待される出力例:**

IBM Semeru Runtimesの場合（Java 21）:
```
openjdk version "21.0.x" 2024-xx-xx
IBM Semeru Runtime Open Edition 21.0.x.x (build 21.0.x+x)
Eclipse OpenJ9 VM 21.0.x.x (build openj9-x.x.x, JRE 21 Windows 10 amd64-64-Bit)
```

OpenJDKの場合（Java 21）:
```
openjdk version "21.0.x" 2024-xx-xx
OpenJDK Runtime Environment (build 21.0.x+x)
OpenJDK 64-Bit Server VM (build 21.0.x+x, mixed mode, sharing)
```

※Java 17をインストールした場合は、バージョン番号が `"17.0.x"` と表示されます

Oracle JDKの場合（Java 21）:
```
java version "21.0.x" 2024-xx-xx LTS
Java(TM) SE Runtime Environment (build 21.0.x+xx-LTS-xxx)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.x+xx-LTS-xxx, mixed mode, sharing)
```

> **注意**: バージョン番号が `17.x.x` 以上であることを確認してください。Java 21推奨。

### 手順3: Javaコンパイラの確認

```cmd
javac -version
```

**期待される出力例:**
```
javac 21.0.x
```

※Java 17をインストールした場合は `javac 17.0.x` と表示されます

### 手順4: Mavenのバージョン確認

```cmd
mvn -version
```

**期待される出力例:**
```
Apache Maven 3.9.6 (xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx)
Maven home: C:\Program Files\Apache\maven\apache-maven-3.9.6
Java version: 21.0.x, vendor: IBM Corporation (or Oracle Corporation, or N/A), runtime: C:\Program Files\Semeru\jdk-21.x.x+x (or other path)
Default locale: ja_JP, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

※Java 17をインストールした場合は `Java version: 17.0.x` と表示されます

> **確認ポイント:**
> - Maven のバージョンが 3.9 以上であること
> - Java version が 17.x.x 以上であること（21.x.x推奨）
> - Maven home と Java runtime のパスが正しいこと

### トラブルシューティング（確認時）

**コマンドが認識されない場合:**
- 環境変数の設定を再確認
- コマンドプロンプトを再起動
- PCを再起動

**バージョンが異なる場合:**
- 複数のJavaがインストールされている可能性
- JAVA_HOMEとPathの設定を再確認


---

## アプリケーションのセットアップ

Java環境の構築が完了したら、TaskManagerアプリケーションをセットアップして起動します。

### 手順1: プロジェクトの配置

1. TaskManagerプロジェクトのフォルダを任意の場所に配置
   - 例: `C:\Users\YourName\Documents\java-task-manager-app`

2. コマンドプロンプトでプロジェクトディレクトリに移動

```cmd
cd C:\Users\YourName\Documents\java-task-manager-app
```

> **注意**: パスは実際のプロジェクトの場所に置き換えてください。

### 手順2: 依存関係のダウンロードとビルド

プロジェクトディレクトリで以下のコマンドを実行:

```cmd
mvn clean install
```

**処理内容:**
- プロジェクトのクリーンアップ
- 必要なライブラリのダウンロード
- アプリケーションのコンパイル
- JARファイルの生成

**所要時間:** 初回は数分かかる場合があります（依存関係のダウンロード）

**成功時の出力例:**
```
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  XX.XXX s
[INFO] Finished at: 2026-XX-XXTXX:XX:XX+09:00
[INFO] ------------------------------------------------------------------------
```

### 手順3: アプリケーションの起動

#### 方法1: Mavenを使用して起動（推奨）

```cmd
mvn spring-boot:run
```

#### 方法2: JARファイルを直接実行

```cmd
java -jar target\task-manager-1.0.0.jar
```

**起動成功時の出力例:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.2)

...
2026-XX-XX XX:XX:XX.XXX  INFO XXXXX --- [           main] c.e.t.TaskManagerApplication             : Started TaskManagerApplication in X.XXX seconds
```

### 手順4: ブラウザでアクセス

アプリケーションが起動したら、ブラウザで以下のURLにアクセス:

```
http://localhost:8080
```

タスク一覧画面が表示されれば成功です！

### 手順5: アプリケーションの停止

コマンドプロンプトで `Ctrl + C` を押してアプリケーションを停止できます。

### H2 Consoleへのアクセス（オプション）

データベースの内容を確認したい場合:

```
http://localhost:8080/h2-console
```

**接続情報:**
- JDBC URL: `jdbc:h2:file:./data/taskdb`
- Username: `sa`
- Password: (空欄)


---

## トラブルシューティング

よくある問題と解決方法をまとめています。

### 問題1: 'java' は、内部コマンドまたは外部コマンド...として認識されていません

**原因:**
- 環境変数が正しく設定されていない
- コマンドプロンプトが古い

**解決方法:**
1. 環境変数の設定を再確認
   - JAVA_HOME が正しく設定されているか
   - Path に `%JAVA_HOME%\bin` が追加されているか
2. コマンドプロンプトを再起動
3. PCを再起動

### 問題2: 'mvn' は、内部コマンドまたは外部コマンド...として認識されていません

**原因:**
- Maven の環境変数が正しく設定されていない

**解決方法:**
1. 環境変数の設定を再確認
   - MAVEN_HOME が正しく設定されているか
   - Path に `%MAVEN_HOME%\bin` が追加されているか
2. コマンドプロンプトを再起動

### 問題3: ポート8080が既に使用されている

**エラーメッセージ例:**
```
Web server failed to start. Port 8080 was already in use.
```

**解決方法:**

#### 方法1: 使用中のプロセスを終了

1. 使用中のポートを確認:
```cmd
netstat -ano | findstr :8080
```

2. プロセスIDを確認して終了:
```cmd
taskkill /PID <プロセスID> /F
```

#### 方法2: アプリケーションのポート番号を変更

[`application.properties`](java-task-manager-app/src/main/resources/application.properties:3) を編集:

```properties
server.port=8081
```

### 問題4: ビルドが失敗する（BUILD FAILURE）

**原因:**
- インターネット接続の問題
- 依存関係のダウンロード失敗
- Mavenのローカルリポジトリの破損

**解決方法:**

1. インターネット接続を確認

2. Mavenのローカルリポジトリをクリーンアップ:
```cmd
mvn clean
```

3. 依存関係を再ダウンロード:
```cmd
mvn clean install -U
```

4. それでも解決しない場合、ローカルリポジトリを削除:
```cmd
rmdir /s /q %USERPROFILE%\.m2\repository
mvn clean install
```

### 問題5: Java のバージョンが異なる

**原因:**
- 複数のJavaバージョンがインストールされている
- JAVA_HOME が古いバージョンを指している

**解決方法:**

1. インストールされているJavaを確認:
```cmd
where java
```

2. JAVA_HOME を正しいJava 17以上のパスに設定

3. 不要な古いJavaをアンインストール（オプション）

### 問題6: 文字化けが発生する

**原因:**
- コマンドプロンプトの文字コードの問題

**解決方法:**

コマンドプロンプトで文字コードを変更:
```cmd
chcp 65001
```

または、PowerShellを使用:
```powershell
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
```

### 問題7: メモリ不足エラー

**エラーメッセージ例:**
```
java.lang.OutOfMemoryError: Java heap space
```

**解決方法:**

Mavenの実行時にメモリを増やす:
```cmd
set MAVEN_OPTS=-Xmx1024m
mvn spring-boot:run
```

または、JARファイル実行時:
```cmd
java -Xmx1024m -jar target\task-manager-1.0.0.jar
```

### 問題8: データベースファイルが見つからない

**原因:**
- 初回起動時の正常な動作
- データフォルダが削除された

**解決方法:**

アプリケーションを起動すると自動的に `data` フォルダと `taskdb.mv.db` ファイルが作成されます。
特に対応は不要です。

### 問題9: ブラウザでアクセスできない

**原因:**
- アプリケーションが起動していない
- ファイアウォールでブロックされている

**解決方法:**

1. アプリケーションが起動しているか確認
   - コマンドプロンプトに "Started TaskManagerApplication" と表示されているか

2. ファイアウォールの設定を確認
   - Windows Defender ファイアウォールで Java を許可

3. 別のブラウザで試す

### サポート情報

上記で解決しない場合は、以下の情報を確認してください:

1. Javaのバージョン: `java -version`
2. Mavenのバージョン: `mvn -version`
3. エラーメッセージの全文
4. 実行したコマンド
