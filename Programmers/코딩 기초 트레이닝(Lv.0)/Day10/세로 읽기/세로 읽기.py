def solution(my_string, m, c):
    answer = ''
    
    for i in range(len(my_string)//m):
        answer += my_string[i*m + c]

    return answer

solution("ihrhbakrfpndopljhygc",4,2)