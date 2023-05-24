def selection_sort(arr):
    for i in range(len(arr)):
        for j in range(i + 1, len(arr)):
            if arr[i] > arr[j]:
                arr[i], arr[j] = arr[j], arr[i]
    return arr

if __name__ == '__main__':
    arr = [89, 56, 45, 34, 65, 76]
    srt = selection_sort(arr)
    print(srt)