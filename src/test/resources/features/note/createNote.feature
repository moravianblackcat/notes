@note
Feature: Note creation
  
  Scenario: Note creation
	When I send POST request to /api/v1/note with request body from note/createNote.json
	Then [2s] A note with some ID and fields from note/createNote.json is created