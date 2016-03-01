
/* Author: Manben Chen : A00937960 */ 
/* Header file for assignment 3 */

#ifndef ASSIGN3_H
#define	ASSIGN3_H
#define NAMESIZE 20
#ifndef BLOCK
#define BLOCK 2  
#endif
#define MIN_ID_SIZE 1000000
#define MAX_ID_SIZE 9999999
#define MIN_SCORE 0
#define MAX_SCORE 100
#define QUIT 4
#define RECORDS_BY_ID 2
#define ENTER_RECORDS 1
#define RECORDS_BY_NAME 3
#define USER_INPUT_BUFFER 128
#define ID_SIZE 64
#define SCORE_SIZE 32

typedef struct {
    char last[NAMESIZE];
    char first[NAMESIZE];
} name;    
    
typedef struct {
    int id;
    name name;
    int score;
} record;
    
typedef struct {
    record *data;
    size_t nalloc;
    size_t nused;
} record_list;
void format_name(char name[]);
void display_record(const record student_record);
int cmp_by_id(const void *p, const void *q);
int ask_for_last_name(name *record_name, int *user_choice);
int ask_for_first_name(name *record_name, int *user_choice);
int ask_for_student_id(record *student_record, int *user_choice);
int ask_for_score(record *student_record, int *user_choice);
int get_user_input();
void displayMenu();
void list_init(record_list *list);
void list_destroy(record_list *list);
void list_insert(record_list *list, const record *rec);
int cmp_by_name(const void *p, const void *q);
#endif
