package com.example.dreamapp.util;

import com.example.dreamapp.model.Dream;
import com.example.dreamapp.model.type.DreamType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DreamInterpreter {
	private static class Interpretation {
		String message;
		int sentiment;
		Interpretation(String message, int sentiment) {
			this.message = message;
			this.sentiment = sentiment;
		}
	}

	private static final Map<String, Interpretation> DICTIONARY = new HashMap<>();

	static {
		DICTIONARY.put("날다", new Interpretation("높은 목표 달성이나 자유로움을 상징합니다.", +1));
		DICTIONARY.put("물", new Interpretation("감정의 흐름 또는 재정적 변화를 의미할 수 있습니다.", 0));
		DICTIONARY.put("뱀", new Interpretation("위험 또는 변화, 또는 치유를 상징합니다.", -1));
		DICTIONARY.put("떨어지다", new Interpretation("불안감이나 통제 상실에 대한 두려움을 나타냅니다.", -1));
		DICTIONARY.put("시험", new Interpretation("압박감이나 도전에 대한 스트레스를 반영할 수 있습니다.", -1));
		DICTIONARY.put("길", new Interpretation("삶의 여정이나 방향성을 고민하는 상태를 의미합니다.", 0));
		DICTIONARY.put("집", new Interpretation("안정감 또는 과거 기억, 혹은 내면 세계를 나타냅니다.", +1));
		DICTIONARY.put("불", new Interpretation("열정, 분노, 정화 또는 위험을 상징할 수 있습니다.", 0));
		DICTIONARY.put("눈", new Interpretation("감정 표현 또는 진실을 보는 것을 의미합니다.", 0));
		DICTIONARY.put("강아지", new Interpretation("충성심이나 우정, 보호욕을 상징합니다.", +1));
		DICTIONARY.put("고양이", new Interpretation("독립성, 신비로움 또는 호기심을 나타냅니다.", 0));
		DICTIONARY.put("자동차", new Interpretation("인생의 진행 상황이나 목표로 향하는 방식을 나타낼 수 있습니다.", 0));
		DICTIONARY.put("비행기", new Interpretation("새로운 가능성이나 멀리 있는 목표를 향한 열망을 상징합니다.", +1));
		DICTIONARY.put("바다", new Interpretation("무의식 또는 감정의 깊이를 나타냅니다.", 0));
		DICTIONARY.put("산", new Interpretation("도전이나 성취, 또는 장애를 상징합니다.", +1));
		DICTIONARY.put("우주", new Interpretation("무한한 가능성이나 존재에 대한 탐구를 나타냅니다.", +1));
		DICTIONARY.put("낯선 사람", new Interpretation("자기 내면의 새로운 측면이나 알지 못하는 요소를 상징합니다.", 0));
		DICTIONARY.put("돈", new Interpretation("가치관이나 자원에 대한 생각을 반영합니다.", 0));
		DICTIONARY.put("문", new Interpretation("기회나 변화의 관문을 의미합니다.", +1));
		DICTIONARY.put("다리", new Interpretation("인간 관계나 상황 간의 연결을 상징합니다.", +1));
		DICTIONARY.put("비", new Interpretation("정화, 슬픔 또는 새로운 시작을 의미할 수 있습니다.", 0));
		DICTIONARY.put("춤", new Interpretation("표현의 자유나 기쁨, 혹은 관계에 대한 상징일 수 있습니다.", +1));
		DICTIONARY.put("노래", new Interpretation("감정 표현이나 소통, 치유의 의미를 가질 수 있습니다.", +1));
		DICTIONARY.put("전화", new Interpretation("연락이나 소통에 대한 기대 혹은 불안감을 나타냅니다.", 0));
		DICTIONARY.put("공연", new Interpretation("주목받고자 하는 마음이나 자신감, 불안을 반영합니다.", 0));
		DICTIONARY.put("길 잃음", new Interpretation("방향성 상실에 대한 불안을 의미합니다.", -1));
		DICTIONARY.put("노인", new Interpretation("지혜나 경험, 혹은 나이 듦에 대한 생각을 상징합니다.", +1));
		DICTIONARY.put("아기", new Interpretation("새로운 시작, 순수함, 책임감을 나타냅니다.", +1));
		DICTIONARY.put("비밀", new Interpretation("숨겨진 감정이나 상황, 또는 해결되지 않은 문제를 상징합니다.", 0));
		DICTIONARY.put("헤어짐", new Interpretation("관계의 변화나 결단을 상징할 수 있습니다.", -1));
		DICTIONARY.put("폭풍", new Interpretation("내적 갈등이나 혼란 상태를 상징합니다.", -1));
		DICTIONARY.put("해", new Interpretation("희망이나 새로운 시작, 빛을 의미할 수 있습니다.", +1));
		DICTIONARY.put("달", new Interpretation("무의식, 감정의 변화, 또는 비밀스러운 면을 나타냅니다.", 0));
		DICTIONARY.put("별", new Interpretation("소망이나 목표, 영감의 상징입니다.", +1));
		DICTIONARY.put("책", new Interpretation("지식 추구나 자기 성찰에 대한 메시지를 반영합니다.", +1));
		DICTIONARY.put("창문", new Interpretation("새로운 시각이나 기회를 의미합니다.", +1));
		DICTIONARY.put("거울", new Interpretation("자기 성찰이나 자아 인식의 상징입니다.", 0));
		DICTIONARY.put("꽃", new Interpretation("성장, 아름다움, 일시적인 행복을 나타냅니다.", +1));
		DICTIONARY.put("숲", new Interpretation("복잡한 감정이나 탐구해야 할 내면 세계를 상징합니다.", 0));
		DICTIONARY.put("탈출", new Interpretation("회피 욕구나 자유에 대한 갈망을 반영합니다.", 0));
		DICTIONARY.put("추격", new Interpretation("압박감이나 불안, 혹은 목표를 향한 집념을 나타냅니다.", 0));
		DICTIONARY.put("숨다", new Interpretation("회피심리나 안전을 원하는 욕구를 상징합니다.", 0));
		DICTIONARY.put("쫓기다", new Interpretation("어떤 문제나 사람으로부터 벗어나고 싶은 심리를 나타냅니다.", -1));
		DICTIONARY.put("도망", new Interpretation("현실에서 피하고 싶은 상황이나 두려움을 반영합니다.", -1));
		DICTIONARY.put("위험", new Interpretation("불안이나 경고 신호를 나타낼 수 있습니다.", -1));
		DICTIONARY.put("갇히다", new Interpretation("답답함이나 제한된 상황에 대한 불안을 상징합니다.", -1));
		DICTIONARY.put("추락", new Interpretation("실패에 대한 두려움 또는 변화 과정을 나타냅니다.", -1));
		DICTIONARY.put("죽다", new Interpretation("종결이나 변화, 혹은 새로운 시작을 암시할 수 있는 상징입니다.", 0));
		DICTIONARY.put("죽음", new Interpretation("종결이나 변화, 혹은 새로운 시작의 의미일 수 있습니다.", 0));
		DICTIONARY.put("죽었다", new Interpretation("과거의 상황이 끝나고 새로운 전환점을 암시할 수 있습니다.", 0));
		DICTIONARY.put("사망", new Interpretation("끝맺음, 또는 삶의 중요한 전환점을 나타낼 수 있습니다.", 0));
		DICTIONARY.put("사망하다", new Interpretation("종결이나 변화, 때로는 두려움을 반영합니다.", 0));
		DICTIONARY.put("숨지다", new Interpretation("내면의 압박이나 종말에 대한 두려움을 상징할 수 있습니다.", -1));
		DICTIONARY.put("살해", new Interpretation("죄책감 또는 억눌린 분노, 갈등의 표현일 수 있습니다.", -1));
		DICTIONARY.put("죽이", new Interpretation("내적 갈등이나 파괴적 감정, 또는 변화 욕구를 반영합니다.", -1));
		DICTIONARY.put("전쟁", new Interpretation("내적 또는 외적 갈등, 대립 상황을 상징합니다.", -1));
		DICTIONARY.put("부활", new Interpretation("재생이나 회복, 새로운 시작을 강하게 암시합니다.", +1));
		DICTIONARY.put("상처", new Interpretation("과거의 아픔이나 치유가 필요한 부분을 나타냅니다.", 0));
		DICTIONARY.put("피", new Interpretation("생명력, 고통, 또는 변화의 대가를 상징할 수 있습니다.", 0));
		DICTIONARY.put("사라지다", new Interpretation("소멸감이나 잃어버림, 또는 새로운 시작을 의미할 수 있습니다.", -1));
		DICTIONARY.put("사라짐", new Interpretation("변화 과정 중의 상실감이나 불안감을 반영합니다.", -1));
		DICTIONARY.put("죽음의 그림자", new Interpretation("종말에 대한 불안 또는 큰 변화가 임박했음을 의미할 수 있습니다.", -1));
	}

	public static String interpret(Dream dream) {
		String content = dream.getContent();
		if (content == null || content.isEmpty()) {
			return "꿈 내용이 비어 있습니다.";
		}
		List<String> positiveList = new ArrayList<>();
		List<String> negativeList = new ArrayList<>();
		List<String> neutralList = new ArrayList<>();
		int totalSentiment = 0;
		for (Map.Entry<String, Interpretation> entry : DICTIONARY.entrySet()) {
			if (content.contains(entry.getKey())) {
				Interpretation interp = entry.getValue();
				totalSentiment += interp.sentiment;
				if (interp.sentiment > 0) positiveList.add(interp.message);
				else if (interp.sentiment < 0) negativeList.add(interp.message);
				else neutralList.add(interp.message);
			}
		}
		if (positiveList.isEmpty() && negativeList.isEmpty() && neutralList.isEmpty()) {
			return "해몽 결과가 없습니다.";
		}
		StringBuilder sb = new StringBuilder();
		if (totalSentiment > 0) {
			sb.append("종합적으로 긍정적인 요소가 우세합니다.\n\n");
		} else if (totalSentiment < 0) {
			sb.append("종합적으로 부정적인 요소가 우세합니다.\n\n");
		} else if (!positiveList.isEmpty() || !negativeList.isEmpty()) {
			sb.append("긍정적 요소와 부정적 요소가 혼재된 복합적인 꿈입니다.\n\n");
		}
		if (!positiveList.isEmpty()) {
			sb.append("긍정적 요소:\n");
			for (String msg : positiveList) {
				sb.append("- ").append(msg).append("\n");
			}
			sb.append("\n");
		}
		if (!negativeList.isEmpty()) {
			sb.append("부정적 요소:\n");
			for (String msg : negativeList) {
				sb.append("- ").append(msg).append("\n");
			}
			sb.append("\n");
		}
		if (!neutralList.isEmpty()) {
			sb.append("중립적 요소:\n");
			for (String msg : neutralList) {
				sb.append("- ").append(msg).append("\n");
			}
		}
		return sb.toString().trim();
	}

	public static boolean isGoodDream(Dream dream) {
		DreamType type = dream.getType();
		return type == DreamType.POSITIVE || type == DreamType.LUCID;
	}
}
