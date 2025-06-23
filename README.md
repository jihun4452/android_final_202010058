# 🌙 DreamApp – 안드로이드 꿈 일기장

“어젯밤 당신의 꿈은 무엇이었나요?”  
DreamApp은 **꿈 기록**과 **간단한 꿈 해석**을 지원하는 안드로이드 애플리케이션입니다.  
데이터베이스를 별도로 두지 않고, **해시 기반(메모리) 저장소**로 간단하게 동작하도록 설계되었습니다.

---

## 주요 기능
| 기능 | 설명 |
|------|------|
| 꿈 작성·저장 | 제목·내용·꿈 유형(긍정‧부정 등)·해몽 설명 표시 여부 입력 |
| 꿈 목록 보기 | 최신순 정렬, RecyclerView로 스크롤 |
| 꿈 상세 조회 | 기록한 내용·타입 아이콘·자동 해석 결과 확인 |
| 자동 해석 | 꿈 내용에 포함된 **키워드**를 하드코딩 사전에서 매칭해 긍/부정 메시지 생성 |
| 테마 | 노을·저녁 톤 그라디언트 배경 + 몽환적 구름 PNG 오브젝트 |
| 오프라인 전용 | 별도 DB·네트워크 없이 로컬 메모리에서 즉시 실행 |

---

## 기술 스택
| 구분 | 사용 기술 |
|------|-----------|
| **언어** | Kotlin 1.9 / 일부 Java |
| **Android** | SDK 35, minSdk 24 (desugaring으로 `java.time` 사용) |
| **UI** | ConstraintLayout, FlexboxLayout, Material Components |
| **빌드** | Gradle KTS, ViewBinding |
| **기타** | coreLibraryDesugaring, Glide(선택) |

---

##  데이터 저장 방식
> **DB 대신 간단한 메모리 캐시!**

```java
public class DreamRepository {
    private static final List<Dream> DREAMS = new ArrayList<>();

    public List<Dream> getAllDreams() { … }
    public void addDream(Dream dream) { … }
}
```

##  꿈 해석 로직
```java
DICTIONARY.put("날다", new Interpretation("높은 목표 달성…", +1));
DICTIONARY.put("뱀",   new Interpretation("위험 또는 변화…", -1));
```

1. 사용자가 입력한 꿈 내용에서 **키워드 포함 여부**를 검사  
2. 각 키워드의 **감성 점수(±1)**를 합산  
   - `> 0` → **긍정적**  
   - `< 0` → **부정적**  
   - `= 0` → **긍·부 혼재(복합)**  
3. 매칭된 메시지를 **카테고리별(긍정·부정·중립)** 로 정리하여 출력


## 영상

https://github.com/user-attachments/assets/b8ccdba8-4bc0-4160-9fd0-b10281712efe

## 사진

### 랜딩 페이지
![image](https://github.com/user-attachments/assets/d3ee63ec-6809-4048-abd0-99b5f2c5669f)

### 메인 페이지
![image](https://github.com/user-attachments/assets/8b759a37-21f6-4e77-bab2-6f89c182ecea)

### 꿈 게시물 작성 페이지
![image](https://github.com/user-attachments/assets/0a9e536a-95ba-4d46-91cb-49e7f00660fb)

### 꿈 게시물 상세 조회 페이지
<img width="395" alt="image" src="https://github.com/user-attachments/assets/9b285a8f-9d81-4b1f-90d2-96f2924dd5ea" />
