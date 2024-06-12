plugins {
    id("buildlogic.kotlin-library-conventions")
}

dependencies {
    implementation(libs.gdx)
    implementation(libs.gdx.gltf)
    api(libs.ktx)
    api(libs.ktx.scene2d)
}