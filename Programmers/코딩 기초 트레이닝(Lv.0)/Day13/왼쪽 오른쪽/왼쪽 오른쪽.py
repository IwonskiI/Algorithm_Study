def solution(str_list):
    answer = []
    
    for i in range(len(str_list)):
        if str_list[i] == "l":
            for j in range(i):
                answer.append(str_list[j])
            break
        elif str_list[i] == "r":
            for j in range(i+1,len(str_list)):
                answer.append(str_list[j])
            break
    
    return answer