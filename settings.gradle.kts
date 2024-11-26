rootProject.name = "servico-exemplo"

dependencyResolutionManagement {
    val repoUrl  = "https://nexus-repository.com.br/repository"
    val repoPath = "/releases/"

    repositories {
        maven {
            url = uri(repoUrl + repoPath)
        }
        mavenCentral()
    }

    val group    = "br.com.projetos"
    val artifact = "bom-exemplo"
    val version  = "0.4.3"

    versionCatalogs {
        create("bom") {
            from("$group:$artifact:$version")
        }
    }
}