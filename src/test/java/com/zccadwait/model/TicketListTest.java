package com.zccadwait.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketListTest {
    @Test
    public void testEmpty(){
        TicketList ticketList = TicketList.parseTicketList("{\"tickets\":[],\"next_page\":null,\"previous_page\":null,\"count\":0}");
        assertEquals(ticketList.getTickets().size(), 0);
        assertNull(ticketList.getNextPage());
        assertNull(ticketList.getPreviousPage());
        assertEquals(ticketList.getCount(), 0);
    }

    @Test
    public void testDefault(){
        TicketList ticketList = TicketList.parseTicketList(
                "{\n" +
                        "  \"tickets\": [\n" +
                        "    {\n" +
                        "      \"url\": \"https://zccadwait.zendesk.com/api/v2/tickets/1.json\",\n" +
                        "      \"id\": 1,\n" +
                        "      \"external_id\": null,\n" +
                        "      \"via\": {\n" +
                        "        \"channel\": \"sample_ticket\",\n" +
                        "        \"source\": {\n" +
                        "          \"from\": {},\n" +
                        "          \"to\": {},\n" +
                        "          \"rel\": null\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"created_at\": \"2021-11-24T23:46:29Z\",\n" +
                        "      \"updated_at\": \"2021-11-24T23:46:29Z\",\n" +
                        "      \"type\": \"incident\",\n" +
                        "      \"subject\": \"Sample ticket: Meet the ticket\",\n" +
                        "      \"raw_subject\": \"Sample ticket: Meet the ticket\",\n" +
                        "      \"description\": \"Hi there,\\n\\nI’m sending an email because I’m having " +
                        "a problem setting up your new product. Can you help me troubleshoot?\\n" +
                        "\\nThanks,\\n The Customer\\n\\n\",\n" +
                        "      \"priority\": \"normal\",\n" +
                        "      \"status\": \"open\",\n" +
                        "      \"recipient\": null,\n" +
                        "      \"requester_id\": 422170776391,\n" +
                        "      \"submitter_id\": 1903604482087,\n" +
                        "      \"assignee_id\": 1903604482087,\n" +
                        "      \"organization_id\": null,\n" +
                        "      \"group_id\": 4414618588685,\n" +
                        "      \"collaborator_ids\": [],\n" +
                        "      \"follower_ids\": [],\n" +
                        "      \"email_cc_ids\": [],\n" +
                        "      \"forum_topic_id\": null,\n" +
                        "      \"problem_id\": null,\n" +
                        "      \"has_incidents\": false,\n" +
                        "      \"is_public\": true,\n" +
                        "      \"due_at\": null,\n" +
                        "      \"tags\": [\n" +
                        "        \"sample\",\n" +
                        "        \"support\",\n" +
                        "        \"zendesk\"\n" +
                        "      ],\n" +
                        "      \"custom_fields\": [],\n" +
                        "      \"satisfaction_rating\": null,\n" +
                        "      \"sharing_agreement_ids\": [],\n" +
                        "      \"fields\": [],\n" +
                        "      \"followup_ids\": [],\n" +
                        "      \"ticket_form_id\": 360003551111,\n" +
                        "      \"brand_id\": 360007080251,\n" +
                        "      \"allow_channelback\": false,\n" +
                        "      \"allow_attachments\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"next_page\": null,\n" +
                        "  \"previous_page\": null,\n" +
                        "  \"count\": 1\n" +
                        "}");
        assertNotNull(ticketList);
        assertEquals(ticketList.getTickets().size(), 1);
        assertEquals(ticketList.getTickets().get(0).getId(), 1);
        assertNull(ticketList.getNextPage());
        assertNull(ticketList.getNextPage());
    }

    @Test
    public void testMultipleTickets(){
        TicketList ticketList = TicketList.parseTicketList(
                "{\n" +
                        "  \"tickets\": [\n" +
                        "    {\n" +
                        "      \"url\": \"https://zccadwait.zendesk.com/api/v2/tickets/1.json\",\n" +
                        "      \"id\": 1,\n" +
                        "      \"external_id\": null,\n" +
                        "      \"via\": {\n" +
                        "        \"channel\": \"sample_ticket\",\n" +
                        "        \"source\": {\n" +
                        "          \"from\": {},\n" +
                        "          \"to\": {},\n" +
                        "          \"rel\": null\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"created_at\": \"2021-11-24T23:46:29Z\",\n" +
                        "      \"updated_at\": \"2021-11-24T23:46:29Z\",\n" +
                        "      \"type\": \"incident\",\n" +
                        "      \"subject\": \"Sample ticket: Meet the ticket\",\n" +
                        "      \"raw_subject\": \"Sample ticket: Meet the ticket\",\n" +
                        "      \"description\": \"Hi there,\\n\\nI’m sending an email because I’m having " +
                        "a problem setting up your new product. Can you help me troubleshoot?\\n" +
                        "\\nThanks,\\n The Customer\\n\\n\",\n" +
                        "      \"priority\": \"normal\",\n" +
                        "      \"status\": \"open\",\n" +
                        "      \"recipient\": null,\n" +
                        "      \"requester_id\": 422170776391,\n" +
                        "      \"submitter_id\": 1903604482087,\n" +
                        "      \"assignee_id\": 1903604482087,\n" +
                        "      \"organization_id\": null,\n" +
                        "      \"group_id\": 4414618588685,\n" +
                        "      \"collaborator_ids\": [],\n" +
                        "      \"follower_ids\": [],\n" +
                        "      \"email_cc_ids\": [],\n" +
                        "      \"forum_topic_id\": null,\n" +
                        "      \"problem_id\": null,\n" +
                        "      \"has_incidents\": false,\n" +
                        "      \"is_public\": true,\n" +
                        "      \"due_at\": null,\n" +
                        "      \"tags\": [\n" +
                        "        \"sample\",\n" +
                        "        \"support\",\n" +
                        "        \"zendesk\"\n" +
                        "      ],\n" +
                        "      \"custom_fields\": [],\n" +
                        "      \"satisfaction_rating\": null,\n" +
                        "      \"sharing_agreement_ids\": [],\n" +
                        "      \"fields\": [],\n" +
                        "      \"followup_ids\": [],\n" +
                        "      \"ticket_form_id\": 360003551111,\n" +
                        "      \"brand_id\": 360007080251,\n" +
                        "      \"allow_channelback\": false,\n" +
                        "      \"allow_attachments\": true\n" +
                        "    }\n," + "{" +
                        "      \"id\": 2,\n" +
                        "      \"url\": \"https://zccadwait.zendesk.com/api/v2/tickets/2.json\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"next_page\": null,\n" +
                        "  \"previous_page\": null,\n" +
                        "  \"count\": 1\n" +
                        "}");
        assertNotNull(ticketList);
        assertEquals(ticketList.getTickets().size(), 2);
        assertEquals(ticketList.getTickets().get(0).getId(), 1);
        assertEquals(ticketList.getTickets().get(1).getId(), 2);
        assertNull(ticketList.getNextPage());
        assertNull(ticketList.getNextPage());
    }
}
