def solution(my_strings, parts):
    answer = ''
    
    for i in range(len(my_strings)):
        for j in range(parts[i][0],parts[i][1]+1):
            answer += my_strings[i][j]
    
    return answer