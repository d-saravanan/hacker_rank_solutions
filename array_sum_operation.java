package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("input05.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(("output05.txt")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int N = Integer.parseInt(firstMultipleInput[0]);

        int M = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> op = IntStream.range(0, M).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Long> result = performOperations(N, op);
        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<Long> performOperations(int N, List<Integer> op) {
        Long sum = Long.valueOf(((Long.valueOf(N)) * ((1 + Long.valueOf(N)))) / 2);
        Integer first = 1, last = N, tmp;

        List<Long> result = new ArrayList<>();

        for (Integer opi : op) {

            if (first.equals(opi) || last.equals(opi) || (opi > 1 && opi < N)) {
                tmp = first;
                first = last;
                last = tmp;
            } else {
                sum = (sum - last) + opi;
                last = opi;
            }

            result.add(sum);
        }
        return result;
    }
}
