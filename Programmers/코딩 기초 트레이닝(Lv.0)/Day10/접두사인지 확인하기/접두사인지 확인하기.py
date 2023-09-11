def solution(my_string, is_suffix):
    answer = 0
    lists=[]
    for i in range(len(my_string)):
        lists.append(my_string[:i])
    if is_suffix in lists:
        answer = 1
    return answer