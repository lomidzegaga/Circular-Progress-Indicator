
<div align="center">
  <a href="https://www.linkedin.com/in/gaga-lomidze/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/>
  </a>
</div>

# 🚀 Jetpack Compose Circular Progress Indicator

This project demonstrates a custom Circular Progress Indicator built with Jetpack Compose. It showcases two animation modes:

* `Immediate Start` - The animation begins as soon as the composable is loaded.
* `Button-Controlled Start` - The animation starts only when the user presses a button.

# 🎥 Demo
🔹 Auto-Start Animation

https://github.com/user-attachments/assets/21f9c114-48ff-4c85-83bf-47b19b8eaead

🔹 Button-Controlled Animation

https://github.com/user-attachments/assets/82ed20d3-8cbe-4077-8291-b72429fbd36a

# 📌 Features

✅ Customizable duration (seconds, minutes, hours, days)  
✅ Adjustable stroke width, colors, and radius  
✅ Supports immediate and delayed animation start  

# 🛠️ Usage

```kotlin
// Auto-start animation
CircularProgressIndicator()

// Button-controlled animation
val isAnimationStart = remember { mutableStateOf(false) }

Button(onClick = { isAnimationStart.toggle() }) {
   Text("Start/Stop Animation")
}

// Simple Extension for change isAnimationStart value
fun MutableState<Boolean>.toggle() {
   value = !value
}

CircularProgressIndicator(startAnimation = isAnimationStart)
```



