

![CreateHosting](https://cdn.discordapp.com/attachments/785493649969381396/1151964355534016602/CREATE_ENCASED.png)

<p>Curseforge page: <a href="https://www.curseforge.com/minecraft/mc-mods/create-encased">https://www.curseforge.com/minecraft/mc-mods/create-encased</a></p>


![Bisect hosting](https://www.bisecthosting.com/partners/custom-banners/04b018a6-2b05-42f7-bc73-448bb3ee940c.webp)
for developper:
```groovy
repositories {
    maven {
        // Iglee's repo
        url = "http://maven.iglee.fr/"
        content {
            includeGroup "fr.iglee42"
        }
    }
}
```

```
implementation fg.deobf("fr.iglee42:CreateCasing:${mc_version}-${create_encased_version}
```

<a href="http://maven.iglee.fr/#/releases/fr/iglee42/CreateCasing">
        <img src="https://flat.badgen.net/maven/v/metadata-url/http/50.20.249.21:8080/releases/fr/iglee42/CreateCasing/maven-metadata.xml?color=cf9555&label=CreateEncased" alt="Create Encased Latest Version">
    </a>