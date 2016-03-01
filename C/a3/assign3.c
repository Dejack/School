/* Author: Manben Chen : A00937960 */
/* Assignment 3  */
/* Program to handle student records */
#include <stdio.h>
#include <stdlib.h>
#include "assign3.h"
#include <string.h>
#include <ctype.h>

int main(void) {
    int userInput;
    size_t i;
    record student_record;
    name student_name;
    record_list student_list; 
    student_list.nalloc = 0;
    student_list.nused = 0;

    list_init(&student_list);
    do {
        displayMenu();
        userInput = get_user_input();
        
        if(userInput == ENTER_RECORDS) {
            while (userInput != EOF) {
                while (userInput != EOF && ask_for_student_id(&student_record, &userInput)) {
                    ;
                }
                while(userInput != EOF && ask_for_last_name(&student_name, &userInput)) {
                    ;
                }
                while (userInput != EOF && ask_for_first_name(&student_name, &userInput)) {
                    ;
                }

                while (userInput != EOF && ask_for_score(&student_record, &userInput)) {
                    ;
                }
                if (userInput != EOF) {
                    student_record.name = student_name;
                    list_insert(&student_list, &student_record);
                    fprintf(stderr, "Please enter another record.\n");
                }
            }
        }
        if (userInput == RECORDS_BY_ID) {
            if (student_list.nused == 0) {
                fprintf(stderr, "No data to display.\n");
            } else {
                qsort(student_list.data, student_list.nused, sizeof(student_list.data[0]), cmp_by_id);
                for (i = 0; i < student_list.nused; i++) {
                    display_record(student_list.data[i]);
                }
            }
        }
        
        if (userInput == RECORDS_BY_NAME) {
            if (student_list.nused == 0) {
                fprintf(stderr, "No data to display.\n");
            } else {
                qsort(student_list.data, student_list.nused, sizeof(student_list.data[0]), cmp_by_name);
                for (i = 0; i < student_list.nused; i++) {
                    display_record(student_list.data[i]);
                }
            }
        }
        
    } while (userInput != QUIT);
    
    list_destroy(&student_list);
    return 0;
}



/* Displays the menu to the screen */
void displayMenu() {
    fprintf(stderr, "1. Enter records\n");
    fprintf(stderr, "2. Display records sorted by ID\n");
    fprintf(stderr, "3. Display records sorted by name\n");
    fprintf(stderr, "4. Quit\n");
}
/* Gets user input from stdin and validates it */
int get_user_input() {
    char userInput[USER_INPUT_BUFFER];
    int user_choice;
    if ((fgets(userInput, USER_INPUT_BUFFER, stdin) == 0)) {
        fprintf(stderr, "Invalid Choice.\n");
        return 0;
    };
    if ((sscanf(userInput, "%d", &user_choice) != 1)) {
        fprintf(stderr, "Invalid Choice.\n");
        return 0;
    }
    if (user_choice == 1) {
        return ENTER_RECORDS;
    } else if(user_choice == RECORDS_BY_ID) {
        return RECORDS_BY_ID;
    } else if (user_choice == RECORDS_BY_NAME) {
        return RECORDS_BY_NAME;
    } else if (user_choice == QUIT) {
        return QUIT;
    } else {
        fprintf(stderr, "Invalid Choice.\n");
        return 0;
    }
}
/* initializes the record list to the empty list */
void list_init(record_list *list) {
#ifdef DEBUG
    fprintf(stderr, "#\n");
#endif
    list->data = malloc(BLOCK * sizeof(record));

    list->nused = 0;
    list->nalloc = BLOCK;   
}
/* Frees dynamically located memory */
void list_destroy(record_list *list) {
#ifdef DEBUG
    fprintf(stderr, "@\n");
#endif
    free(list->data);
    list->nused = 0;
    list->nalloc = 0;
}

/* Inserts a record into the record list */
void list_insert(record_list *list, const record *rec) {
    if (list->nused == list->nalloc) {
        record *tmp = realloc(list->data, (list->nalloc + BLOCK) * sizeof(record));
        if (tmp == 0) {
            fprintf(stderr, "Unable to resize dynamic array.\n");
            return;
        }
#ifdef DEBUG
        fprintf(stderr, "#\n");
#endif
        list->nalloc += BLOCK;
        list->data = tmp;
    } 
    list->data[list->nused] = *rec;
    list->nused++;
}

/* Prompts the user to enter a last name.*/
/* Returns 1 if name is invalid, 0 otherwise.*/
int ask_for_last_name(name *record_name, int *user_choice) {
    char userInput[USER_INPUT_BUFFER];
    char lastName[NAMESIZE];
    fprintf(stderr, "Please enter a last name.\n");
    if ((fgets(userInput, USER_INPUT_BUFFER, stdin) == 0)) {
        *user_choice = EOF;
        return 1;
    };
    if ((sscanf(userInput, "%s", lastName)) != 1) {
        fprintf(stderr, "Invalid last name.\n");
        return 1;
    }
    
    if (strlen(lastName) >= NAMESIZE) { 
        fprintf(stderr, "Invalid last name.\n");
        return 1;
    }
    format_name(lastName);
    strncpy(record_name->last, lastName, NAMESIZE);
    return 0;
}

/* Prompts the user to enter a first name.*/
/* Returns 1 if name is invalid, 0 otherwise.*/
int ask_for_first_name(name *record_name, int *user_choice) {
    char userInput[USER_INPUT_BUFFER];
    char firstName[NAMESIZE];
    fprintf(stderr, "Please enter a first name.\n");
    if ((fgets(userInput, USER_INPUT_BUFFER, stdin) == 0)) {
        *user_choice = EOF;
        return 1;
    };
    if ((sscanf(userInput, "%s", firstName)) != 1) {
        fprintf(stderr, "Invalid first name.\n");
        return 1;
    }
    
    if (strlen(firstName) >= NAMESIZE) {
        fprintf(stderr, "Invalid first name.\n");
        return 1;
    }
    format_name(firstName);
    strncpy(record_name->first, firstName, NAMESIZE);
    return 0;
}

/* Prompts the user to enter a student ID.*/
/* Returns 1 if name is invalid, 0 otherwise.*/
int ask_for_student_id(record *student_record, int *user_choice) {
    char userInput[ID_SIZE];
    int student_id;
    
    fprintf(stderr, "Please enter a student id.\n");
    if ((fgets(userInput, USER_INPUT_BUFFER, stdin) == 0)) {
        *user_choice = EOF;
        return 1;
    };
    if ((sscanf(userInput, "%d", &student_id)) != 1) {
        return 1;
    }
    if (student_id == 0) {
        *user_choice = EOF;
        return 1;
    }
    if (student_id > MAX_ID_SIZE || student_id < MIN_ID_SIZE) {
        fprintf(stderr, "Invalid student id.\n");
        return 1;
    }
    student_record->id = student_id;
    return 0;
}

/* Prompts the user to enter a score.*/
/* Returns 1 if name is invalid, 0 otherwise.*/
int ask_for_score(record *student_record, int *user_choice) {
    char userInput[SCORE_SIZE];
    int score;
    fprintf(stderr, "Please enter a score.\n");
    if ((fgets(userInput, USER_INPUT_BUFFER, stdin) == 0)) {
        *user_choice = EOF;
        return 1;
    };
    if ((sscanf(userInput, "%d", &score)) != 1) {
        fprintf(stderr, "Invalid score.\n");
        return 1;
    }
    if (score > MAX_SCORE || score < MIN_SCORE) {
        fprintf(stderr, "Invalid score.\n");
        return 1;
    }
    student_record->score = score;
    return 0;
}

/* Function to pass into qsort. Makes it sort in ascending order.*/
int cmp_by_id(const void *p, const void *q) {
    const record *pp = p;
    const record *qq = q;
    return pp->id - qq->id;
}

void display_record(const record student_record) {
    printf("%d %s, %s: %d\n", student_record.id, student_record.name.last,
            student_record.name.first, student_record.score);
}
/* Function to pass into qsort. Makes it sort by last name in lexicographical order */
int cmp_by_name(const void *p, const void *q) {
    const record *pp = p;
    const record *qq = q;
    
    int names_equal = strncmp(pp->name.last, qq->name.last, NAMESIZE);
    if (names_equal) {
        return names_equal;
    } else {
        names_equal = strncmp(pp->name.first, qq->name.first, NAMESIZE);
    }
    if (names_equal) {
        return names_equal;
    } else {
        return pp->id - qq->id;
    }
}
/* Changes the strings first letter to capital and the rest to lowercase.*/
void format_name(char name[]) {
    size_t i;
    if (strlen(name) > 0) {
        name[0] = toupper((unsigned)name[0]);
        for (i = 1; name[i] != 0; i++) {
            name[i] = tolower((unsigned)name[i]);
        }
    }
}