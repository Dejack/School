/* Author: Manben Chen : A00937960 */
/* Created on November 2, 2015, 5:27 PM */
/* COMP 2510 - Assignment 02 */
/* This program compares two text files */
/* This comparison can be done in 3 modes character-by-character, word-by-word or line-by-line */
/* There is also a option to do those comparisons case sensitively or not */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define WORDSIZE 512
#define LINESIZE 1024
#define LINE_SWITCH "-l"
#define WORD_SWITCH "-w"
#define CASE_SWITCH "-i"
#define LINE_SWITCH_OPTION 1
#define WORD_SWITCH_OPTION 2
#define CASE_SWITCH_OPTION 3
#define END_SWITCH_OPTION 4
#define TRUE 1
#define END_SWITCH "--"
#define SWITCH_LEN 2
#define FILE_AMOUNT 2

int set_option(char **p, size_t size, int *line, int *word, int *sen, size_t *index);
int is_valid_switch(const char a[]);
int set_files(char **p, size_t size, size_t index, FILE **fp1, FILE **fp2);
void usage();
int comp_char(char **p, size_t index, FILE **fp1, FILE **fp2, int case_insensitive);
int comp_line(char **p, size_t index, FILE **fp1, FILE **fp2, int case_insensitive);
int comp_word(char **p, size_t index, FILE **fp1, FILE **fp2, int case_insensitive);

int main(int argc, char** argv) {
    FILE *fp1, *fp2;
    int line_by_line = 0, word_by_word = 0, case_insensitive = 0;
    size_t index_of_files = 0;
    int char_return, line_return, word_return;
    
    /* Check to see if switches are valid, if so set switches. */
    if (!set_option(argv, argc, &line_by_line, &word_by_word, &case_insensitive, &index_of_files)) {
        return 2;
    }
    /* Check to see if valid, if so set pointers. */
    if (!set_files(argv, argc, index_of_files, &fp1, &fp2)) {
        return 2;
    }
    
    if (line_by_line) {
        line_return = comp_line(argv, index_of_files, &fp1, &fp2, case_insensitive);
        if (fclose(fp1) == EOF) {
            perror("fclose fp1");
            return 3;
        }
        if (fclose(fp2) == EOF) {
            perror("fclose fp2");
            return 3;
        }
        return line_return;
    } else if (word_by_word) {
        word_return = comp_word(argv, index_of_files, &fp1, &fp2, case_insensitive);
         if (fclose(fp1) == EOF) {
            perror("fclose fp1");
            return 3;
        }
        if (fclose(fp2) == EOF) {
            perror("fclose fp2");
            return 3;
        }
        return word_return;
    } else {
        char_return = comp_char(argv, index_of_files, &fp1, &fp2, case_insensitive);  
                 if (fclose(fp1) == EOF) {
            perror("fclose fp1");
            return 3;
        }
        if (fclose(fp2) == EOF) {
            perror("fclose fp2");
            return 3;
        }
        return char_return;
    }
}

/* Precondition: Needs to be passed argv, argc, address of line_by_line, word_by_word, and case_sensitive */
/* Postcondition: line_by_line, word_by_word, case_sensitive will be set to 1 if the switches are present. */
/* The index of the end of switches will be set */
/* Return 1 on success, 0 on failure. */
int set_option(char **p, size_t size, int *line, int *word, int *sen, size_t *index) {
    char input[LINESIZE];
    int option;
    size_t i;
    for (i = 1; i < size; i++) {
        if ((sscanf(p[i], "%s", input) != EOF)) {
            if ((!(option = is_valid_switch(input)) && input[0] == '-')) {
                fprintf(stderr, "Invalid option: %s", input);
                return 0;
            } else if (!(option = is_valid_switch(input))) {
                *index = i;
                return 1;
            }
        } else {
            perror("sscanf, cannot read input");
            exit(TRUE);
        }
        
        if (option == LINE_SWITCH_OPTION) {
            *line = TRUE;
        } else if (option == WORD_SWITCH_OPTION) {
            *word = TRUE;
        } else if (option == CASE_SWITCH_OPTION) {
            *sen = TRUE;
        } else if (option == END_SWITCH_OPTION) {
            *index = i+1;
            return 1;
        }
            
        if (*line == TRUE && *word == TRUE) {
            fprintf(stderr, "Can't specify both -l & -w");
            usage();
            return 0;
        }
        *index = i;

    }
    return 1;
}

/* Precondition: a[] must contain a string of length 2 */
/* Postcondition: returns 1 if it is a line switch, 2 if it is a word switch, 3 if it is a case switch, 0 if not valid */
int is_valid_switch(const char a[]) {
    if (strlen(a) != SWITCH_LEN) {
        return 0;
    }
    if (strcmp(a, LINE_SWITCH) == 0) {
        return 1;
    } else if (strcmp(a, WORD_SWITCH) == 0) {
        return 2;
    } else if (strcmp(a, CASE_SWITCH) == 0) {
        return 3;
    } else if (strcmp(a, END_SWITCH) == 0) {
        return 4;
    } else {
        return 0;
    }
}

/* Precondition: Requires argv, return value of set_option, argc, and two file pointers */
/* Postcondition: The file pointers will be assigned files */
/* Exits the program if file is unable to be opened. */
/* Returns 1 on success, 0 on failure.*/
int set_files(char **p, size_t size, size_t index, FILE **fp1, FILE **fp2) {
    if ((size - index) > FILE_AMOUNT) {
        fprintf(stderr, "Too many input files.");
        usage();
        return 0;
    } else if ((size - index) < FILE_AMOUNT) {
        fprintf(stderr, "Too few input files.");
        usage();
        return 0;
    } 
        
   /* if (strcmp(p[index], p[index+1]) == 0) {
        fprintf(stderr, "Cannot use the same file!");
        return 2;
    }
    */
    
    if ((*fp1 = fopen(p[index], "r")) == NULL) {
        perror("fopen fp2");
        return 0;
    }
    if ((*fp2 = fopen(p[index+1], "r")) == NULL) {
        perror("fopen fp2");
        return 0;
    }
    return 1;
    
}
/* Precondition: None */
/* Postcondition: Prints out a message to stderr explaining how to invoke the program. */
void usage() {
    fprintf(stderr, "\n How to use: comp [option]... file1 file2\nWhere file1 & file2 are the 2 input "
            "files to compare and [option] contains the switch -i, -l or -w\n");
}

/* Precondition: Pass in two file pointers with files opened already and the case sensitivity */
/* Postcondition: Compares both files character by character. Outputs messages based on where the files differ. */
/* return 1 if the 2 files are not equal */
/* 2 if it is not invoked correctly; */
/* 3 if there are other errors (e.g., can't open files). */
int comp_char(char **p, size_t index, FILE **fp1, FILE **fp2, int case_insensitive) {
    int fp1_char, fp2_char, count = 1;
    while (((fp1_char = fgetc(*fp1)) != EOF) & ((fp2_char = fgetc(*fp2)) != EOF)) { 
        if (!case_insensitive) {
            if (fp1_char == fp2_char) {
                count++;
            } else {
                printf("files differ: char %d\n", count);
                return 1;
            }      
        } else {
             if (tolower(fp1_char) == tolower(fp2_char)) {
                count++;
            } else {
                printf("files differ: char %d\n", count);
                return 1;
            }      
        }
    }
    
    if (fp1_char == EOF && fp2_char == EOF) {
        printf("files are equal\n");
        return 0;
    } else if (fp1_char == EOF) {
        printf("EOF on %s\n", p[index]);
        return 1;
    } else if (fp2_char == EOF) {
        printf("EOF on %s\n", p[index+1]);
        return 1;
    }   
}
/* Precondition: Pass in two file pointers with files opened already and the case sensitivity. File line size cannot exceed 1024 characters*/
/* Postcondition: Compares both files line by line. Outputs messages based on where the files differ. */
/* Returns 0 if the 2 files are equal */
/* return 1 if the 2 files are not equal */
/* 2 if it is not invoked correctly; */
/* 3 if there are other errors (e.g., can't open files). */
int comp_line(char **p, size_t index, FILE **fp1, FILE **fp2, int case_insensitive) {
    int count = 1;
    char fp1_line[LINESIZE], fp2_line[LINESIZE];
    char *word_one, *word_two;
    char *word_one_token_p, *word_two_token_p;
    char fp1_line_value, fp2_line_value;
    int lines_equal = TRUE;
    
    while (((fp1_line_value = fgets(fp1_line, LINESIZE, *fp1)) != 0) & ((fp2_line_value = fgets(fp2_line, LINESIZE, *fp2)) != 0)) { 

        word_one = strtok_r(fp1_line, " ", &word_one_token_p);
        word_two = strtok_r(fp2_line, " ", &word_two_token_p);        
        
        while ((word_one != 0) || (word_two != 0)) {
             if (!case_insensitive) {
                 if (word_one == 0) {
                      printf("files differ: line %d\n", count);
                      return 1;
                } else if (word_two == 0) {
                      printf("files differ: line %d\n", count);
                      return 1;
                }
                if (word_two[strlen(word_two)-2] == '\r') {
                    if (strncmp(word_one, word_two, strlen(word_two)-2) != 0) {
                    printf("files differ: line %d\n", count);
                    return 1;
                    }
                } else if (strcmp(word_one, word_two) != 0) {
                    printf("files differ: line %d\n", count);
                    return 1;
                }
            } else {
                 if (word_one == 0) {
                      printf("files differ: line %d\n", count);
                      return 1;
                 } else if (word_two == 0) {
                     printf("files differ: line %d\n", count);
                     return 1;
                 }
                 if (word_two[strlen(word_two)-2] == '\r') {
                    if (strncasecmp(word_one, word_two, strlen(word_two)-2) != 0) {
                    printf("files differ: line %d\n", count);
                    return 1;
                    } 
                } else if (strcasecmp(word_one, word_two) != 0) {
                    printf("files differ: line %d\n", count);
                    return 1;
                }
            }
            word_one = strtok_r(NULL, " ", &word_one_token_p);
            word_two = strtok_r(NULL, " ", &word_two_token_p);
            
        }
        count++;
    }
    
    if ((fp1_line_value == NULL) && (fp2_line_value == NULL)) {
        printf("files are equal\n");
        return 0;
    } else if (fp1_line_value == NULL) {
        printf("files differ: line %d\n", count-1);
        return 1;
    } else if (fp2_line_value == NULL) {
        printf("files differ: line %d\n", count-1);
        return 1;
    }  
}

/* Precondition: Pass in two file pointers with files opened already and the case sensitivity. File line size cannot exceed 1024 characters*/
/* Postcondition: Compares both files line by line. Outputs messages based on where the files differ. */
/* return 1 if the 2 files are not equal */
/* 2 if it is not invoked correctly; */
/* 3 if there are other errors (e.g., can't open files). */
int comp_word(char **p, size_t index, FILE **fp1, FILE **fp2, int case_insensitive) {
    int count = 1;
    char word_one[WORDSIZE], word_two[WORDSIZE];
    int fp1_read_value, fp2_read_value;
    while (((fp1_read_value = fscanf(*fp1, "%s", word_one)) != EOF) & ((fp2_read_value = fscanf(*fp2, "%s", word_two)) != EOF)) { 
        if (!case_insensitive) {
            if (strncmp(word_one, word_two, WORDSIZE) == 0) {
                count++;
            } else {
                printf("files differ: word %d\n", count);
                return 1;
            }      
        } else {
            if (strncasecmp(word_one, word_two, WORDSIZE) == 0) {
                count++;
            } else {
                printf("files differ: word %d\n", count);
                return 1;
            }      
        }
    }
    
    if ((fp1_read_value == EOF) && (fp2_read_value == EOF)) {
        printf("files are equal\n");
        return 0;
    } else if (fp1_read_value == EOF) {
        printf("EOF on %s\n", p[index]);
        return 1;
    } else if (fp2_read_value == EOF) {
        printf("EOF on %s\n", p[index+1]);
        return 1;
    }  
}