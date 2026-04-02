# TaskManager - macOS環境構築手順書

## 目次
1. [概要](#概要)
2. [前提条件](#前提条件)
3. [必要なソフトウェア](#必要なソフトウェア)
4. [Homebrewのインストール](#homebrewのインストール)
5. [Java 21のインストール](#java-21のインストール)
6. [Mavenのインストール](#mavenのインストール)
7. [環境変数の設定](#環境変数の設定)
8. [インストールの確認](#インストールの確認)
9. [アプリケーションのセットアップ](#アプリケーションのセットアップ)
10. [トラブルシューティング](#トラブルシューティング)

---

## 概要

このドキュメントは、TaskManagerアプリケーションをmacOS環境で動作させるために必要なJava開発環境の構築手順を説明します。

## 前提条件

- **OS**: macOS 11 (Big Sur) 以降
- **管理者権限**: インストール時に必要
- **インターネット接続**: ソフトウェアのダウンロードに必要
- **ディスク空き容量**: 最低2GB以上推奨

## 必要なソフトウェア

| ソフトウェア | バージョン | 用途 |
|------------|----------|------|
| Homebrew | 最新版 | macOS用パッケージマネージャー |
| Java Development Kit (JDK) | 21 | Javaアプリケーションの実行環境 |
| Apache Maven | 3.9以上 | ビルドツール・依存関係管理 |
| Git | 最新版 | ソースコード管理（オプション） |


---

## Homebrewのインストール

Homebrewは、macOS用のパッケージマネージャーで、ソフトウェアのインストールを簡単にします。

### 手順1: Homebrewがインストールされているか確認

ターミナルを開いて以下のコマンドを実行:

```bash
brew --version
```

バージョンが表示されれば、Homebrewは既にインストールされています。次のセクションに進んでください。

### 手順2: Homebrewのインストール（未インストールの場合）

1. ターミナルを開く（アプリケーション > ユーティリティ > ターミナル）

2. 以下のコマンドを実行:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

3. パスワードの入力を求められたら、macOSのログインパスワードを入力

4. インストールが完了するまで待機（数分かかる場合があります）

5. Apple Silicon Mac（M1/M2/M3）の場合、以下のコマンドを実行してPATHを設定:

```bash
echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> ~/.zprofile
eval "$(/opt/homebrew/bin/brew shellenv)"
```

Intel Macの場合は、この手順は不要です。

6. インストールの確認:

```bash
brew --version
```

---

## Java 21のインストール

macOSでは、Homebrewを使用してJavaを簡単にインストールできます。

### 方法1: Homebrewを使用したインストール（推奨）

#### 手順1: 利用可能なJavaバージョンを確認

```bash
brew search openjdk
```

#### 手順2: OpenJDK 21をインストール

```bash
brew install openjdk@21
```

#### 手順3: シンボリックリンクの作成

```bash
sudo ln -sfn /opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-21.jdk
```

Intel Macの場合:
```bash
sudo ln -sfn /usr/local/opt/openjdk@21/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-21.jdk
```

> **注意**: 管理者パスワードの入力が必要です。

### 方法2: Oracle JDKを使用したインストール

#### 手順1: JDKのダウンロード

1. Oracle JDK公式サイトにアクセス
   - URL: https://www.oracle.com/java/technologies/downloads/#java21

2. **macOS**セクションから以下のいずれかをダウンロード
   - **Apple Silicon (M1/M2/M3)**: `ARM64 DMG Installer`
   - **Intel Mac**: `x64 DMG Installer`

#### 手順2: JDKのインストール

1. ダウンロードした `.dmg` ファイルをダブルクリック

2. インストーラーのパッケージをダブルクリック

3. インストールウィザードの指示に従う

4. 「インストール」をクリック

5. 管理者パスワードを入力

6. インストールが完了したら「閉じる」をクリック


---

## Mavenのインストール

### 方法1: Homebrewを使用したインストール（推奨）

最も簡単な方法は、Homebrewを使用することです。

```bash
brew install maven
```

インストールが完了すると、Mavenは自動的にPATHに追加されます。

### 方法2: 手動インストール

#### 手順1: Mavenのダウンロード

1. Apache Maven公式サイトにアクセス
   - URL: https://maven.apache.org/download.cgi

2. **Binary tar.gz archive**をダウンロード
   - 例: `apache-maven-3.9.6-bin.tar.gz`
   - 最新の3.9.x以上のバージョンを選択

#### 手順2: Mavenの配置

1. ダウンロードしたファイルを解凍:

```bash
cd ~/Downloads
tar -xzf apache-maven-3.9.6-bin.tar.gz
```

2. 解凍したフォルダを適切な場所に移動:

```bash
sudo mv apache-maven-3.9.6 /opt/
```

3. シンボリックリンクを作成（バージョン管理を容易にするため）:

```bash
sudo ln -s /opt/apache-maven-3.9.6 /opt/maven
```


---

## 環境変数の設定

macOSでは、シェルの設定ファイルに環境変数を追加します。使用しているシェルによって設定ファイルが異なります。

### 手順1: 使用しているシェルを確認

```bash
echo $SHELL
```

**出力例:**
- `/bin/zsh` → zshを使用（macOS Catalina以降のデフォルト）
- `/bin/bash` → bashを使用

### 手順2: 環境変数の設定（Homebrewでインストールした場合）

Homebrewでインストールした場合、通常は環境変数の設定は不要です。次のセクション「インストールの確認」に進んでください。

### 手順3: 環境変数の設定（手動インストールした場合）

#### zshを使用している場合

1. `.zshrc` ファイルを編集:

```bash
nano ~/.zshrc
```

2. ファイルの最後に以下を追加:

```bash
# Java 21
export JAVA_HOME=$(/usr/libexec/java_home -v 21)

# Maven（手動インストールの場合のみ）
export MAVEN_HOME=/opt/maven
export PATH=$MAVEN_HOME/bin:$PATH
```

3. ファイルを保存して閉じる（`Ctrl + X`、`Y`、`Enter`）

4. 設定を反映:

```bash
source ~/.zshrc
```

#### bashを使用している場合

1. `.bash_profile` ファイルを編集:

```bash
nano ~/.bash_profile
```

2. ファイルの最後に以下を追加:

```bash
# Java 21
export JAVA_HOME=$(/usr/libexec/java_home -v 21)

# Maven（手動インストールの場合のみ）
export MAVEN_HOME=/opt/maven
export PATH=$MAVEN_HOME/bin:$PATH
```

3. ファイルを保存して閉じる（`Ctrl + X`、`Y`、`Enter`）

4. 設定を反映:

```bash
source ~/.bash_profile
```

> **注意**: 
> - `JAVA_HOME`は、`/usr/libexec/java_home`コマンドを使用して自動的に設定されます
> - Homebrewでインストールした場合、`MAVEN_HOME`の設定は不要です


---

## インストールの確認

環境変数の設定が完了したら、正しくインストールされているか確認します。

### 手順1: ターミナルを開く

新しいターミナルウィンドウを開きます（アプリケーション > ユーティリティ > ターミナル）

### 手順2: Javaのバージョン確認

```bash
java -version
```

**期待される出力例:**
```
openjdk version "21.0.x" 2024-xx-xx LTS
OpenJDK Runtime Environment (build 21.0.x+xx-LTS)
OpenJDK 64-Bit Server VM (build 21.0.x+xx-LTS, mixed mode, sharing)
```

または（Oracle JDKの場合）:
```
java version "21.0.x" 2024-xx-xx LTS
Java(TM) SE Runtime Environment (build 21.0.x+xx-LTS-xxx)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.x+xx-LTS-xxx, mixed mode, sharing)
```

> **注意**: バージョン番号が `21.x.x` であることを確認してください。

### 手順3: Javaコンパイラの確認

```bash
javac -version
```

**期待される出力例:**
```
javac 21.0.x
```

### 手順4: JAVA_HOMEの確認

```bash
echo $JAVA_HOME
```

**期待される出力例:**
```
/Library/Java/JavaVirtualMachines/openjdk-21.jdk/Contents/Home
```

または
```
/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home
```

### 手順5: Mavenのバージョン確認

```bash
mvn -version
```

**期待される出力例:**
```
Apache Maven 3.9.6 (xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx)
Maven home: /opt/homebrew/Cellar/maven/3.9.6/libexec
Java version: 21.0.x, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/openjdk-21.jdk/Contents/Home
Default locale: ja_JP, platform encoding: UTF-8
OS name: "mac os x", version: "14.x", arch: "aarch64", family: "mac"
```

> **確認ポイント:**
> - Maven のバージョンが 3.9 以上であること
> - Java version が 21.x.x であること
> - Maven home と Java runtime のパスが正しいこと

### トラブルシューティング（確認時）

**コマンドが認識されない場合:**
- 環境変数の設定を再確認
- ターミナルを再起動
- `source ~/.zshrc` または `source ~/.bash_profile` を実行

**バージョンが異なる場合:**
- 複数のJavaがインストールされている可能性
- `/usr/libexec/java_home -V` で全てのJavaバージョンを確認
- JAVA_HOMEの設定を再確認


---

## アプリケーションのセットアップ

Java環境の構築が完了したら、TaskManagerアプリケーションをセットアップして起動します。

### 手順1: プロジェクトの配置

1. TaskManagerプロジェクトのフォルダを任意の場所に配置
   - 例: `~/Documents/java-task-manager-app`

2. ターミナルでプロジェクトディレクトリに移動

```bash
cd ~/Documents/java-task-manager-app
```

> **注意**: パスは実際のプロジェクトの場所に置き換えてください。

### 手順2: 依存関係のダウンロードとビルド

プロジェクトディレクトリで以下のコマンドを実行:

```bash
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

```bash
mvn spring-boot:run
```

#### 方法2: JARファイルを直接実行

```bash
java -jar target/task-manager-1.0.0.jar
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

ターミナルで `Ctrl + C` を押してアプリケーションを停止できます。

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

### 問題1: 'java' コマンドが見つからない

**原因:**
- Javaがインストールされていない
- 環境変数が正しく設定されていない
- ターミナルが古い

**解決方法:**
1. Javaのインストールを確認:
```bash
/usr/libexec/java_home -V
```

2. 環境変数の設定を再確認
3. ターミナルを再起動
4. `source ~/.zshrc` または `source ~/.bash_profile` を実行

### 問題2: 'mvn' コマンドが見つからない

**原因:**
- Mavenがインストールされていない
- 環境変数が正しく設定されていない（手動インストールの場合）

**解決方法:**

#### Homebrewでインストールした場合:
```bash
brew list maven
```
でインストールを確認。インストールされていない場合:
```bash
brew install maven
```

#### 手動インストールの場合:
1. 環境変数の設定を再確認
2. `echo $MAVEN_HOME` でパスを確認
3. ターミナルを再起動

### 問題3: ポート8080が既に使用されている

**エラーメッセージ例:**
```
Web server failed to start. Port 8080 was already in use.
```

**解決方法:**

#### 方法1: 使用中のプロセスを終了

1. 使用中のポートを確認:
```bash
lsof -i :8080
```

2. プロセスIDを確認して終了:
```bash
kill -9 <PID>
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
```bash
mvn clean
```

3. 依存関係を再ダウンロード:
```bash
mvn clean install -U
```

4. それでも解決しない場合、ローカルリポジトリを削除:
```bash
rm -rf ~/.m2/repository
mvn clean install
```

### 問題5: 複数のJavaバージョンがインストールされている

**原因:**
- 複数のJavaバージョンがインストールされている
- JAVA_HOMEが古いバージョンを指している

**解決方法:**

1. インストールされているJavaを確認:
```bash
/usr/libexec/java_home -V
```

2. 特定のバージョンを使用するようにJAVA_HOMEを設定:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

3. 不要な古いJavaをアンインストール（オプション）:
```bash
sudo rm -rf /Library/Java/JavaVirtualMachines/jdk-<version>.jdk
```

### 問題6: Homebrewのインストールに失敗する

**原因:**
- Xcode Command Line Toolsがインストールされていない
- 権限の問題

**解決方法:**

1. Xcode Command Line Toolsをインストール:
```bash
xcode-select --install
```

2. Homebrewを再インストール:
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### 問題7: Apple Silicon Mac (M1/M2/M3) でのパスの問題

**原因:**
- Apple Silicon MacではHomebrewのインストールパスが異なる

**解決方法:**

Apple Silicon Macの場合、Homebrewは `/opt/homebrew` にインストールされます。

`.zshrc` に以下を追加:
```bash
eval "$(/opt/homebrew/bin/brew shellenv)"
```

### 問題8: 権限エラー（Permission denied）

**原因:**
- ファイルやディレクトリへのアクセス権限がない

**解決方法:**

1. プロジェクトディレクトリの権限を確認:
```bash
ls -la ~/Documents/java-task-manager-app
```

2. 必要に応じて権限を変更:
```bash
chmod -R 755 ~/Documents/java-task-manager-app
```

### 問題9: データベースファイルが見つからない

**原因:**
- 初回起動時の正常な動作
- dataフォルダが削除された

**解決方法:**

アプリケーションを起動すると自動的に `data` フォルダと `taskdb.mv.db` ファイルが作成されます。
特に対応は不要です。

### 問題10: ブラウザでアクセスできない

**原因:**
- アプリケーションが起動していない
- ファイアウォールでブロックされている

**解決方法:**

1. アプリケーションが起動しているか確認
   - ターミナルに "Started TaskManagerApplication" と表示されているか

2. ファイアウォールの設定を確認
   - システム環境設定 > セキュリティとプライバシー > ファイアウォール

3. 別のブラウザで試す

### サポート情報

上記で解決しない場合は、以下の情報を確認してください:

1. Javaのバージョン: `java -version`
2. Mavenのバージョン: `mvn -version`
3. macOSのバージョン: `sw_vers`
4. エラーメッセージの全文
5. 実行したコマンド
