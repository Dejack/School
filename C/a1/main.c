/* 
 * COMP 2510 Assignment 1
 * File:   main.c
 * Author: Manben Chen : A00937960
 * Created on October 14, 2015, 8:36 AM
 * Program that reads student records from a file or appends them.
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>


/* Symbolic constants */
#define LINESIZE 512
#define TRUE 1
#define NAMESIZE 50
#define STUDENT_ID_SIZE 50
#define MAX_VALID 100
#define MAX_ID_LENGTH 9
#define MAX_NAME_LENGTH 20
#define MIN_NAME_LENGTH 2
#define NUM_OF_ARGUMENTS 2
#define RECORD_SIZE 54
#define PROPER_RECORD_SIZE 4

/* Prototypes */
void display_instructions();
int is_valid_score(int n);
int is_valid_id(const char s[]);
int is_valid_name(const char s[]);
void to_lower_case(char s[]);

/*
 * 
 */
int main(int argc, char** argv) {
    
    char user_input[LINESIZE];
    int user_input_integer;
    FILE *fp;
    char first_name[NAMESIZE];
    char last_name[NAMESIZE];
    char student_id[STUDENT_ID_SIZE] = "";
    int score = -1;
    int user_exit = 1;
    int abs_user_input;
    int records_printed;
    
    if (argc != NUM_OF_ARGUMENTS) {
        fprintf(stderr, "Error: no file name");
        return 2;
    }
    
    if ((fp = fopen(argv[1], "ab+")) == NULL) {
        perror("fopen");
        return 1;
    }
    
    while(TRUE) {
        display_instructions();
        
        /* Get input from user from stdin, if fails clears the stream and exits the loop */
        if (!fgets(user_input, LINESIZE, stdin)) {
            clearerr(stdin);
            break;
        }
        if (sscanf(user_input, "%d", &user_input_integer) == 1) {
            /* Display records up to user_input_integer */
            if (user_input_integer < 0) {
                abs_user_input = abs(user_input_integer);
                if ((fseek(fp, (RECORD_SIZE * abs_user_input - RECORD_SIZE), SEEK_SET)) != 0) { /* move forward 5 bytes */
                    perror("fseek");
                }
                while (TRUE) {
                    if (fscanf(fp, "%s %s %s %d", student_id, first_name, last_name, &score) != PROPER_RECORD_SIZE) {
                        if (records_printed == 0) {
                            fprintf(stderr, "no matching record\n");
                        }
                        break;
                    } else {
                        fprintf(stderr, "%s : %s, : %s : %d\n", student_id, last_name, first_name, score);
                        records_printed++;
                    }  
                }
                 /* Reset variables */ 
                student_id[0] = '0';
                first_name[0] = '-';
                last_name[0] = '-';
                score = -1;
                records_printed = 0;
            }
            
            /* Append a new record */
            if (user_input_integer == 0) {
                
                /* Until a valid ID is entered */
                while (is_valid_id(student_id)) {
                    printf("Please enter a student ID: ");
                    if (!fgets(user_input, LINESIZE, stdin)) {
                        clearerr(stdin);
                    }
                    if (sscanf(user_input, "%s", student_id) == EOF) {
                        user_exit = 0;
                        break;
                    }
                }
                
                /* Until a valid name is entered */
                while ((is_valid_name(first_name) || (is_valid_name(last_name))) && user_exit) {
                    printf("Please enter a name: ");
                    if (!fgets(user_input, LINESIZE, stdin)) {
                        clearerr(stdin);
                    }
                    if (sscanf(user_input, "%s %s", first_name, last_name) == EOF) {
                        user_exit = 0;
                        break;
                    }
                }
                
                /* Until a valid score is entered */
                while ((is_valid_score(score)) && user_exit) {
                    printf("Please enter a score: ");
                    if (!fgets(user_input, LINESIZE, stdin)) {
                        clearerr(stdin);
                    }
                    if (sscanf(user_input, "%d", &score) == EOF) {
                        user_exit = 0;
                        break;
                    }
                }
                
                to_lower_case(first_name);
                to_lower_case(last_name);
                if (user_exit != 0) {
                    fprintf(fp, "%-10s%-20s%-20s%-4d", student_id, (first_name), (last_name), score);
                }
                user_exit = 1;
                
                /* Reset variables */ 
                student_id[0] = '0';
                first_name[0] = '-';
                last_name[0] = '-';
                score = -1;
            }
            
            /* Display record at user_input_integer line if it exists */
            if (user_input_integer > 0) {
                if ((fseek(fp, (RECORD_SIZE * user_input_integer - RECORD_SIZE), SEEK_SET)) != 0) { /* move forward 5 bytes */
                    perror("fseek");
                }
                if (fscanf(fp, "%s %s %s %d", student_id, first_name, last_name, &score) != PROPER_RECORD_SIZE) {
                    fprintf(stderr, "no matching record\n");
                } else {
                    fprintf(stderr, "%s : %s, : %s : %d\n", student_id, last_name, first_name, score);
                }   
                /* Reset variables */ 
                student_id[0] = '0';
                first_name[0] = '-';
                last_name[0] = '-';
                score = -1;
               
            }
        }
        
    }

    if (fclose(fp) != 0) {
	perror("fclose");
    }
    
    return 0;
}

/* Precondition: none */
/* Postcondition: Displays the instructions to the screen */
void display_instructions() {
        printf("Instructions:\n");
        printf("Enter any integer > 0 to display a record if it exists in the file.\n");
        printf("Enter 0 to add a record.\n");
        printf("Enter any integer < 0 to display all records in the file\n");   
        printf("Please enter a integer: ");  
}

/* Precondition: n must be a integer */
/* Checks to see if n is valid (between 1-100) */
/* Postcondition: returns 0 if it is valid, return 1 if not valid */

int is_valid_score(const int n) {
    if (n >= 0 && n <= MAX_VALID) {
        return 0;
    } else {
        return 1;
    }
}

/* Precondition: strlen(s) >= 1 */
/* Checks to see if char s[] is a valid id */
/* Postcondition: returns 0 if it is valid, return non-zero if not valid */
int is_valid_id(const char s[]) {
    
    size_t i;
    
    if (s[0] != 'a') {
        return 1;
    }
    if (strlen(s) != MAX_ID_LENGTH) {
        return 2;
    }
    
    for (i = 1; s[i] != 0; i++) {
        if (isdigit((int)s[i]) == 0) {
            return 3;
        }
    }
    return 0;
    
}

/* Precondition: strlen(s) >= 1 */
/* Checks to see if char s[] is a valid name */
/* A valid name must have at least 2 characters & fewer than 20 characters; */
/* Each character must be either an alphabet or a hyphen & the name cannot start or end with a hyphen*/
/* Postcondition: returns 1 if valid. Negative values if invalid */
int is_valid_name(const char s[]) {
    
    size_t i;
    if (strlen(s) > MAX_NAME_LENGTH || strlen(s) < MIN_NAME_LENGTH) {
        return 1;
    }
    
    if (s[0] == '-' || s[strlen(s)-1] == '-') {
        return 2;
    }
    
    for (i = 0; s[i] != 0; i++) {
        if (((isalpha((int)s[i])) == 0) && (s[i] != '-')) {
            return 3;
        }
    }
    return 0;
    
}

/* Changes the string to all lowercase */
/* Precondition: s[] must be an array. Cannot be a string constant */
void to_lower_case(char s[]) {
    size_t i;
    for (i = 0; s[i] != '\0'; i++) {
        s[i] = tolower((int)s[i]);
    }
}