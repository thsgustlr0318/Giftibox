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
