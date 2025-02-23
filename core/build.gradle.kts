plugins {
    id("buildlogic.kotlin-library-conventions")
}

dependencies {
    implementation(libs.gdx)
    api(libs.ktx)
    api(libs.ktx.scene2d)
}