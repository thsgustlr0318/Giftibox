# 🧨Trouble Shooting

---

#### MainActivity에서 Fragment로 intent사용해서 데이터 전달 오류

##### ❓Problem

Null Pointer 에러

##### 💡Solution

To be continued..

---

#### Android OS 9 Pie버전부터 http 사용시 네트워크 에러

##### ❓Problem

Android OS 9 Pie버전부터는 "http://" URL 접근을 권장하지 않습니다. (http 접근이 불가능한 것은 아닙니다.)
kakao map api는 http 통신을 통해서 제공하기 때문에 해당 에러가 발생하였습니다.

##### Error

    E/net.daum.mf.map.n.api.NativeBaseNetConnection: Cleartext HTTP traffic to ot2.maps.daum-img.net not permitted,url=http://ot2.maps.daum-img.net/tile/hd/I/L4/1000/446.jpg

##### 💡Solution

network_security_config.xml 파일을 통해서 특정 http 통신에 대해 예외처리 해야합니다. [참조 링크](https://developer.android.com/training/articles/security-config?hl=ko)

---

#### uri to path API deprecated 문제

##### ❓Problem
android 에서 미디어 파일이 변경될 가능성이 있는 경우, 실제 파일 주소를 반환하지 않고 MediaScanner를 작동시킴
이 파일은 MediaStore에 저장되고, content 스키마를 통해 표현
이렇게 스키마로 표현된 url로는 이미지를 불러올 수 없기 때문에 uri를 통해 path를 가져와야 함
그래서 원래 `MediaStore.Images.Media.DATA`컬럼을 통해 값을 가져왔는데 

```kotlin
DATA

This constant was deprecated in API level 29. 
Apps may not have filesystem permissions to directly access this path. 
Instead of trying to open this path directly, 
apps should use ContentResolver#openFileDescriptor(Uri, String) to gain access.
```

deprecated 되었다고 함..

##### 💡Solution
[도큐먼트](https://developer.android.com/training/data-storage/shared/documents-files?hl=ko) 를 참고해서 

```kotlin
private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor: ParcelFileDescriptor? =
            contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor!!.close()
        return image
    }
```
와 같이 `openFileDescriptor`로 변경해주었음
