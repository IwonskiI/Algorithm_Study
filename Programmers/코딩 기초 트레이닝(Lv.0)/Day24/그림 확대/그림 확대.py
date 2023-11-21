def solution(picture, k):
    answer = []
    string = ""
    
    for i in range(len(picture)):
        for _ in range(k):
            string = ""
            for j in range(len(picture[0])):
                for _ in range(k):
                    string += picture[i][j]
            answer.append(string)
    
    
    return answer