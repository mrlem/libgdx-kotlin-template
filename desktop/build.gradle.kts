plugins {
    id("buildlogic.kotlin-common-conventions")
}

dependencies {
    implementation(project(":core"))

    implementation(libs.gdx.backend.lwjgl3)
    implementation("${libs.gdx.platform.get()}:natives-desktop")
}

val assetsDir = file("../core/src/main/resources")
val mainClassName = "org.mrlem.sample.desktop.AppKt"

// Use this task to run the game if IntelliJ run application configuration doesn't work.
tasks.register<JavaExec>("run") {
    setup()
}

tasks.register<JavaExec>("debug") {
    setup()
    debug = true
}

// Use this task to create a fat jar.
tasks.register<Jar>("dist") {
    from(files(sourceSets.main.get().output.classesDirs))
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    from(assetsDir)

    manifest {
        attributes["Main-Class"] = mainClassName
    }
}

fun JavaExec.setup() {
    mainClass.set(mainClassName)
    classpath = sourceSets.main.get().runtimeClasspath
    standardInput = System.`in`
    workingDir = assetsDir
    isIgnoreExitValue = true

    if ("mac" in System.getProperty("os.name").lowercase()) {
        jvmArgs("-XstartOnFirstThread")
    }
}