# Vconsol Java client library

[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

## Installation

### Requirements

- Java 1.8 or later

### Gradle users

Add it in your root build.gradle at the end of repositories:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add this dependency to your project's build file:

```groovy
dependencies {
    implementation 'com.github.aankur:vconsol-java-client:Tag'
}
```

### Maven users

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add this dependency to your project's POM:

```xml

<dependency>
    <groupId>com.github.aankur</groupId>
    <artifactId>vconsol-java-client</artifactId>
    <version>Tag</version>
</dependency>
```

## Usage

VconsolExample.java

```java
import com.techgentsia.model.User;
import com.techgentsia.net.VconsolResponseHandlerOptions;
import com.techgentsia.param.user.UserCreateParam;

public class VconsolJavaClientApplication {

  public static class VconsolOptions extends VconsolResponseHandlerOptions {

    @Override
    public String getApiBase() {
      return "https://your-tenant";
    }

    @Override
    public String getApiKey() {
      return "your api key";
    }

    @Override
    public String getApiSecret() {
      return "Your api secret";
    }

    @Override
    public int getConnectTimeoutMs() {
      return Vconsol.getConnectTimeoutMs();
    }

    @Override
    public int getReadTimeoutMs() {
      return Vconsol.getReadTimeoutMs();
    }
  }

  public static void main(String[] args) {

    VconsolClient vconsolClient = new VconsolClient(new VconsolOptions());

    User user = vconsolClient.users().createUser(
      UserCreateParam.builder()
        .email("sample@example.com")
        .firstName("first")
        .lastName("last")
        .build());
    System.out.println(user.toString());
  }

}

```

### Per-request Configuration

All the request methods accept an optional `RequestOptions` object. Optional
