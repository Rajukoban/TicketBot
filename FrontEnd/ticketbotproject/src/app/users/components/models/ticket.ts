import { TicketPriority } from "./ticketPriority";
import { TicketStatus } from "./ticketStatus";
import { User } from "./user";

export interface Ticket {
    ticketId?: number;
    title: string;
    description: string;
    createdDate?: string;
    status: TicketStatus;
    priority: TicketPriority;
    user: User;
  }