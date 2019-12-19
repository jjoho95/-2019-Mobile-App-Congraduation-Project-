package com.example.congraduation;

import java.util.ArrayList;
import java.util.HashMap;

public class LectureData {
    String computer =
            "소프트웨어와문제해결 공학전공 3\n" +
            "이산수학 전공기반 3\n" +
            "이산수학 전공필수 3\n" +
            "대화형프로그래밍기초 기본소양 3\n" +
            "프로그래밍기초 공학전공 6\n" +
            "프로그래밍기초 전공필수 6\n" +
            "컴퓨터학개론 공학전공 3\n" +
            "기초창의공학설계 공학전공 3\n" +
            "기초창의공학설계 전공필수 3\n" +
            "자료구조 공학전공 3\n" +
            "자료구조 전공필수 3\n" +
            "확률및통계 전공기반 3\n" +
            "자료구조응용 공학전공 3\n" +
            "소프트웨어설계 공학전공 3\n" +
            "자바프로그래밍 공학전공 3\n" +
            "자바프로그래밍 전공필수 3\n" +
            "디지털설계및실험 공학전공 3\n" +
            "오토마타및형식언어 공학전공 3\n" +
            "컴퓨터구조 공학전공 3\n" +
            "컴퓨터구조 전공필수 3\n" +
            "SW융합커뮤니케이션 기본소양 3\n" +
            "컴퓨터윤리 기본소양 3\n" +
            "시스템프로그래밍 공학전공 3\n" +
            "시스템프로그래밍 전공필수 3\n" +
            "논리회로 공학전공 3\n" +
            "자료구조프로그래밍 공학전공 3\n" +
            "공학수학1 전공기반 3\n" +
            "선형대수 전공기반 3\n" +
            "운영체제 공학전공 3\n" +
            "운영체제 전공필수 3\n" +
            "알고리즘2 공학전공 3\n" +
            "알고리즘1 공학전공 3\n" +
            "알고리즘1 전공필수 3\n" +
            "데이타베이스 공학전공 3\n" +
            "컴파일러 공학전공 3\n" +
            "데이타통신 공학전공 3\n" +
            "SW프로젝트관리기법 기본소양 3\n" +
            "모바일앱프로그래밍1 공학전공 3\n" +
            "소프트웨어공학 공학전공 3\n" +
            "프로그래밍언어론 공학전공 3\n" +
            "네트워크프로그래밍 공학전공 3\n" +
            "수치해석 전공기반 3\n" +
            "종합설계프로젝트1 공학전공 4\n" +
            "종합설계프로젝트1 전공필수 4\n" +
            "데이터과학기초 전공기반 3\n" +
            "인공지능 공학전공 3\n" +
            "프로그래밍언어론 공학전공 3\n" +
            "컴퓨터그래픽스 공학전공 3\n" +
            "무선네트워크 공학전공 3\n" +
            "컴퓨터망 공학전공 3\n" +
            "소프트웨어특강 공학전공 3\n" +
            "정보검색 공학전공 3\n" +
            "디지털미디어아트 공학전공 3\n" +
            "모바일앱프로그래밍2 공학전공 3\n" +
            "정보보호론 공학전공 3\n" +
            "종합설계프로젝트2 공학전공 4\n" +
            "소프트웨어디자인패턴 공학전공 3\n" +
            "인간과컴퓨터상호작용 공학전공 3\n" +
            "기계학습개론 공학전공 3\n" +
            "소프트웨어테스팅이론 공학전공 3\n" +
            "대규모병렬컴퓨팅 공학전공 3\n" +
            "고급문제해결 공학전공 3\n" +
            "의료융합소프트웨어 공학전공 3\n" +
            "고급웹프로그래밍 공학전공 3\n" +
            "컴퓨터비전 공학전공 3\n" +
            "모바일로봇프로그래밍 공학전공 3\n" +
            "컴퓨터게임제작 공학전공 3\n" +
            "수학1 전공기반 3\n" +
            "물리학1 전공기반 3\n" +
            "물리학실험 전공기반 1\n" +
            "문화기술개론 기본소양 3\n" +
            "실용화법 기본소양 3\n" +
            "대학글쓰기 기본소양 3\n" +
            "소셜네트워크 기본소양 3\n" +
            "일반생명과학1 전공기반 3\n" +
            "미래산업과직업선택 기본소양 3\n" +
            "기업가정신과벤처창업 기본소양 3\n" +
            "심화글쓰기 기본소양 3\n" +
            "창의적창업모델개발 기본소양 3\n" +
            "논리와비판적사고 기본소양 3\n" +
            "서양의역사와문화 기본소양 3\n";

    String global =
            "이산수학 전공 3\n" +
            "이산수학 융합전공 3\n" +
            "SW실용영어 전공 3\n" +
            "프로그래밍기초 전공필수 6\n" +
            "프로그래밍기초 전공 6\n" +
            "자료구조 전공필수 3\n" +
            "자료구조 전공 3\n" +
            "자료구조응용 전공 3\n" +
            "소프트웨어수학 전공 3\n" +
            "창의융합설계 전공 3\n" +
            "확률및통계 전공 3\n" +
            "자바프로그래밍 전공 3\n" +
            "자바프로그래밍 융합전공 3\n" +
            "SW진로설계 전공 3\n" +
            "오픈소스프로그래밍 전공 3\n" +
            "알고리즘실습 전공필수 3\n" +
            "알고리즘실습 전공 3\n" +
            "소프트웨어설계 전공 3\n" +
            "운영체제 전공필수 3\n" +
            "운영체제 전공 3\n" +
            "데이타베이스 전공 3\n" +
            "컴퓨터망프로그래밍 전공 3\n" +
            "데이터과학기초 전공 3\n" +
            "SW융합설계1 전공 3\n" +
            "SW공학및테스팅 전공 3\n" +
            "소프트웨어융합특강1 전공 1\n" +
            "모바일앱프로그래밍1 전공 3\n" +
            "모바일앱프로그래밍1 융합전공 3\n" +
            "SW융합설계2 전공 3\n" +
            "소프트웨어융합특강2 전공 1\n" +
            "컴파일러 전공 3\n" +
            "정보보호론 전공 3\n" +
            "인공지능 전공 3\n" +
            "인공지능 융합전공 3\n" +
            "컴퓨터구조 전공 3\n" +
            "컴퓨터그래픽스 전공 3\n" +
            "프로그래밍언어론 전공 3\n" +
            "모바일앱프로그래밍2 전공 3\n" +
            "IT기술경영개론 전공 3\n" +
            "IT기술경영개론 창업교과목 3\n" +
            "스타트업설계 전공 3\n" +
            "스타트업설계 창업교과목 3\n" +
            "클라우드컴퓨팅 전공 3\n" +
            "사물인터넷 전공 3\n" +
            "소프트웨어융합특강4 전공 1\n" +
            "소프트웨어융합특강3 전공 1\n" +
            "SW창업프로젝트 전공 3\n" +
            "SW창업프로젝트 창업교과목 3\n" +
            "IT지식재산권 전공 3\n" +
            "IT지식재산권 창업교과목 3\n" +
            "대규모병렬컴퓨팅 전공 3\n" +
            "인간과컴퓨터상호작용설계 전공 3\n" +
            "소프트웨어디자인패턴 전공 3\n" +
            "고급문제해결 전공 3\n" +
            "컴퓨터비전 전공 3\n" +
            "고급웹프로그래밍 전공 3\n" +
            "컴퓨터게임제작 전공 3\n" +
            "모바일로봇프로그래밍 전공 3\n" +
            "수학1 교양 3\n" +
            "실용화법 교양 3\n" +
            "SW사고기법 교양 3\n" +
            "대학글쓰기 교양 3\n" +
            "기업가정신과벤처창업 교양 3\n" +
            "기업가정신과벤처창업 창업교과목 3\n" +
            "서양의역사와문화 교양 3\n" +
            "미래산업과직업선택 교양 3\n" +
            "미래산업과직업선택 창업교과목 3\n" +
            "창업과경영 교양 3\n" +
            "창업과경영 창업교과목 3\n" +
            "심화글쓰기 교양 3\n" +
            "회계와사회생활 교양 3\n" +
            "회계와사회생활 창업교과목 3\n" +
            "나의첫사업제안서 교양 3\n" +
            "나의첫사업제안서 창업교과목 3\n" +
            "글로벌창업실습개론 교양 3\n" +
            "글로벌창업실습개론 창업교과목 3\n" +
            "창조성개발과혁신창업 교양 3\n" +
            "창조성개발과혁신창업 창업교과목 3\n" +
            "알기쉬운창업마케팅 교양 3\n" +
            "알기쉬운창업마케팅 창업교과목 3\n";

    HashMap<String, ArrayList<String>> computerMap = new HashMap<>();
    HashMap<String, ArrayList<String>> globalMap = new HashMap<>();
    HashMap<String, Integer> index = new HashMap<>();

    public LectureData(){
        int cnt = 0;
        String[] lines = computer.split("\n");
        for (String line : lines){
            String[] tokens = line.split(" ");
            if (computerMap.containsKey(tokens[0])){
                computerMap.get(tokens[0]).add(tokens[1]);
                continue;
            }
            computerMap.put(tokens[0], new ArrayList<String>());
            computerMap.get(tokens[0]).add(0, tokens[2]);
            computerMap.get(tokens[0]).add(1, tokens[1]);
            index.put(tokens[0], cnt++);
        }
        lines = global.split("\n");
        for (String line : lines){
            String[] tokens = line.split(" ");
            if (globalMap.containsKey(tokens[0])){
                globalMap.get(tokens[0]).add(tokens[1]);
                continue;
            }
            globalMap.put(tokens[0], new ArrayList<String>());
            globalMap.get(tokens[0]).add(0, tokens[2]);
            globalMap.get(tokens[0]).add(1, tokens[1]);
            index.put(tokens[0], cnt++);
        }
    }

    public ArrayList<String> getCourseData(){
        ArrayList<String> data =  new ArrayList<>();
        for (String key : computerMap.keySet()){
            String row = "INSERT INTO COURSE (Cnumber, Cname, Credit) VALUES (%d, '%s', %s);";
            data.add(String.format(row, index.get(key), key, computerMap.get(key).get(0)));

        }
        for (String key : globalMap.keySet()){
            if (computerMap.containsKey(key)) continue;
            String row = "INSERT INTO COURSE (Cnumber, Cname, Credit) VALUES (%d, '%s', %s);";
            data.add(String.format(row, index.get(key), key, globalMap.get(key).get(0)));
        }
        return data;
    }

    public ArrayList<String> getCategoryData(){
        ArrayList<String> data =  new ArrayList<>();
        for (String key : computerMap.keySet()){
            String row = "INSERT INTO COURSE_CATEGORY (Major, Cno, Category) VALUES ('%s', %d, '%s');";
            int size = computerMap.get(key).size();
            for (int i = 1; i < size; i++){
                data.add(String.format(row, "computer", index.get(key), computerMap.get(key).get(i)));
            }
        }
        for (String key : globalMap.keySet()){
            String row = "INSERT INTO COURSE_CATEGORY (Major, Cno, Category) VALUES ('%s', %d, '%s');";
            int size = globalMap.get(key).size();
            for (int i = 1; i < size; i++){
                data.add(String.format(row, "global", index.get(key), globalMap.get(key).get(i)));
            }
        }
        return data;
    }
}
