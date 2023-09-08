def solution(my_string, queries):
    answer = ''
    
    for i in range(len(queries)):
        temp1 = ''
        temp2 = ''
        temp3 = ''
        for j in range(0,queries[i][0]):
            temp1 += my_string[j]
        for j in range(queries[i][0],queries[i][1]+1):
            temp2 += my_string[j]
        for j in range(queries[i][1]+1,len(my_string)):
            temp3 += my_string[j]
        
        temp2 = list(temp2)
        temp2.reverse()
        
        my_string = temp1 + ''.join(temp2) + temp3
    answer = my_string
    return answer

solution("rermgorpsam",[[2,3],[0,7],[5,9],[6,10]])