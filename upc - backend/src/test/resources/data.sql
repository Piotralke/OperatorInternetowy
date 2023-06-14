

CREATE TABLE public.clients (
    id bigint NOT NULL,
    address character varying(255),
    balance double precision,
    email character varying(255),
    first_name character varying(255),
    is_business_client boolean,
    last_name character varying(255),
    nip character varying(255),
    password character varying(255),
    pesel character varying(255),
    phone_number character varying(255),
    roles character varying(255),
    uuid uuid
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 35420)
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO postgres;

--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 214
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- TOC entry 217 (class 1259 OID 35430)
-- Name: contracts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contracts (
    contract_id bigint NOT NULL,
    amount double precision,
    end_date timestamp(6) without time zone,
    start_date timestamp(6) without time zone,
    uuid uuid,
    offer_entity_id bigint
);


ALTER TABLE public.contracts OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 35429)
-- Name: contracts_contract_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contracts_contract_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contracts_contract_id_seq OWNER TO postgres;

--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 216
-- Name: contracts_contract_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contracts_contract_id_seq OWNED BY public.contracts.contract_id;


--
-- TOC entry 219 (class 1259 OID 35437)
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    id bigint NOT NULL,
    address character varying(255),
    contract_form smallint,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    nip character varying(255),
    password character varying(255),
    pesel character varying(255),
    phone_number character varying(255),
    roles character varying(255),
    salary double precision,
    uuid uuid,
    workplace character varying(255)
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 35436)
-- Name: employees_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employees_id_seq OWNER TO postgres;

--
-- TOC entry 3443 (class 0 OID 0)
-- Dependencies: 218
-- Name: employees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employees_id_seq OWNED BY public.employees.id;


--
-- TOC entry 221 (class 1259 OID 35446)
-- Name: notices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notices (
    notice_id bigint NOT NULL,
    description character varying(255),
    is_clicked boolean,
    notice_date timestamp(6) without time zone,
    uuid uuid,
    id bigint
);


ALTER TABLE public.notices OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 35445)
-- Name: notices_notice_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.notices_notice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notices_notice_id_seq OWNER TO postgres;

--
-- TOC entry 3444 (class 0 OID 0)
-- Dependencies: 220
-- Name: notices_notice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.notices_notice_id_seq OWNED BY public.notices.notice_id;


--
-- TOC entry 223 (class 1259 OID 35453)
-- Name: offers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offers (
    id bigint NOT NULL,
    description text NOT NULL,
    is_archival boolean,
    name character varying(255),
    offer_type smallint,
    price double precision,
    uuid uuid,
    with_device boolean,
    product_entity_product_id bigint
);


ALTER TABLE public.offers OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 35452)
-- Name: offers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.offers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.offers_id_seq OWNER TO postgres;

--
-- TOC entry 3445 (class 0 OID 0)
-- Dependencies: 222
-- Name: offers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.offers_id_seq OWNED BY public.offers.id;


--
-- TOC entry 225 (class 1259 OID 35462)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    amount double precision,
    order_date timestamp(6) without time zone,
    order_status smallint,
    payment_status smallint,
    uuid uuid,
    id bigint,
    employee_entity_id bigint,
    service_service_id bigint
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 35461)
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_order_id_seq OWNER TO postgres;

--
-- TOC entry 3446 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


--
-- TOC entry 227 (class 1259 OID 35469)
-- Name: payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payments (
    payment_id bigint NOT NULL,
    amount double precision,
    data timestamp(6) without time zone,
    name character varying(255),
    payment_status smallint,
    products_uuid character varying(255),
    uuid uuid,
    contract_id bigint
);


ALTER TABLE public.payments OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 35468)
-- Name: payments_payment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payments_payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payments_payment_id_seq OWNER TO postgres;

--
-- TOC entry 3447 (class 0 OID 0)
-- Dependencies: 226
-- Name: payments_payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payments_payment_id_seq OWNED BY public.payments.payment_id;


--
-- TOC entry 229 (class 1259 OID 35478)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id bigint NOT NULL,
    description text NOT NULL,
    name character varying(255),
    price double precision,
    product_type character varying(255),
    uuid uuid,
    order_id bigint
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 35477)
-- Name: products_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_product_id_seq OWNER TO postgres;

--
-- TOC entry 3448 (class 0 OID 0)
-- Dependencies: 228
-- Name: products_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_product_id_seq OWNED BY public.products.product_id;


--
-- TOC entry 231 (class 1259 OID 35487)
-- Name: services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.services (
    service_id bigint NOT NULL,
    name character varying(255),
    offer_type smallint,
    uuid uuid,
    id bigint,
    contract_id bigint
);


ALTER TABLE public.services OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 35486)
-- Name: services_service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.services_service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.services_service_id_seq OWNER TO postgres;

--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 230
-- Name: services_service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.services_service_id_seq OWNED BY public.services.service_id;


--
-- TOC entry 233 (class 1259 OID 35494)
-- Name: user_problems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_problems (
    user_problem_id bigint NOT NULL,
    description text,
    user_problem_end_date timestamp(6) without time zone,
    user_problem_start_date timestamp(6) without time zone,
    user_problem_status smallint,
    uuid uuid,
    id bigint
);


ALTER TABLE public.user_problems OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 35493)
-- Name: user_problems_user_problem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_problems_user_problem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_problems_user_problem_id_seq OWNER TO postgres;

--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 232
-- Name: user_problems_user_problem_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_problems_user_problem_id_seq OWNED BY public.user_problems.user_problem_id;


--
-- TOC entry 235 (class 1259 OID 35503)
-- Name: warehouses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.warehouses (
    id bigint NOT NULL,
    product_name character varying(255),
    quantity integer,
    uuid uuid,
    product_entity_product_id bigint
);


ALTER TABLE public.warehouses OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 35502)
-- Name: warehouses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.warehouses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.warehouses_id_seq OWNER TO postgres;

--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 234
-- Name: warehouses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.warehouses_id_seq OWNED BY public.warehouses.id;


--
-- TOC entry 3223 (class 2604 OID 35424)
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- TOC entry 3224 (class 2604 OID 35433)
-- Name: contracts contract_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contracts ALTER COLUMN contract_id SET DEFAULT nextval('public.contracts_contract_id_seq'::regclass);


--
-- TOC entry 3225 (class 2604 OID 35440)
-- Name: employees id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees ALTER COLUMN id SET DEFAULT nextval('public.employees_id_seq'::regclass);


--
-- TOC entry 3226 (class 2604 OID 35449)
-- Name: notices notice_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices ALTER COLUMN notice_id SET DEFAULT nextval('public.notices_notice_id_seq'::regclass);


--
-- TOC entry 3227 (class 2604 OID 35456)
-- Name: offers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offers ALTER COLUMN id SET DEFAULT nextval('public.offers_id_seq'::regclass);


--
-- TOC entry 3228 (class 2604 OID 35465)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- TOC entry 3229 (class 2604 OID 35472)
-- Name: payments payment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments ALTER COLUMN payment_id SET DEFAULT nextval('public.payments_payment_id_seq'::regclass);


--
-- TOC entry 3230 (class 2604 OID 35481)
-- Name: products product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN product_id SET DEFAULT nextval('public.products_product_id_seq'::regclass);


--
-- TOC entry 3231 (class 2604 OID 35490)
-- Name: services service_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services ALTER COLUMN service_id SET DEFAULT nextval('public.services_service_id_seq'::regclass);


--
-- TOC entry 3232 (class 2604 OID 35497)
-- Name: user_problems user_problem_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_problems ALTER COLUMN user_problem_id SET DEFAULT nextval('public.user_problems_user_problem_id_seq'::regclass);


--
-- TOC entry 3233 (class 2604 OID 35506)
-- Name: warehouses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouses ALTER COLUMN id SET DEFAULT nextval('public.warehouses_id_seq'::regclass);


--
-- TOC entry 3415 (class 0 OID 35421)
-- Dependencies: 215
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (2, 'Suite 914 63470 Stacee Pike, Lake Irvin, WA 91647', 0, 'nathaniel.herzog@hotmail.com', 'Dawn', false, 'Rohan', NULL, 'client1', NULL, '(563) 696-1933', 'USER', 'e5991df3-707c-4840-9c73-6a463f35680c');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (3, 'Apt. 446 831 Ullrich Estates, Tyraville, IL 71869', 0, 'mandy.stamm@hotmail.com', 'Sandy', false, 'Bosco', NULL, 'client2', NULL, '625-360-2887 x144', 'USER', '109cdaa0-4d80-44d5-9352-01b2172f23fc');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (4, 'Suite 506 79477 Elwood Squares, South Maximina, UT 16712', 0, 'rina.heathcote@yahoo.com', 'Kyoko', false, 'Schroeder', NULL, 'client3', NULL, '(508) 423-7192 x30813', 'USER', 'ecd8a157-25e5-4860-9ec4-12e3035fb9ab');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (5, 'Apt. 812 058 Palma Cove, Kacifurt, OR 84083-5769', 0, 'wilton.greenfelder@hotmail.com', 'Josef', false, 'Hegmann', NULL, 'client4', NULL, '1-581-823-1314', 'USER', 'a5653c9e-6d3c-45e1-865d-0b62fec75031');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (6, '90888 Aufderhar Court, Harveyfort, AR 30733', 0, 'casey.koepp@yahoo.com', 'Martine', false, 'Turner', NULL, 'client5', NULL, '1-742-171-4388', 'USER', '03a9f874-cb97-45cd-a975-08f8c591cc26');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (7, '49017 Hank Locks, Raphaelport, AR 41462-4738', 0, 'marquerite.treutel@gmail.com', 'Enoch', false, 'Kris', NULL, 'client6', NULL, '1-594-933-6718 x724', 'USER', '31099895-42d7-4c72-bb49-7a1e25a6e5c0');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (8, '3611 Ciara Springs, South Lonfurt, MN 58469-7122', 0, 'shavonda.stracke@hotmail.com', 'Elbert', false, 'Paucek', NULL, 'client7', NULL, '1-415-094-0056 x05473', 'USER', '8db5281b-6e17-42b1-ab0c-a216877115bb');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (9, '3855 Dylan Rest, West Maire, MT 21021-0454', 0, 'ferdinand.mcclure@gmail.com', 'Kristin', false, 'Harris', NULL, 'client8', NULL, '609-218-4096', 'USER', '2809b0d9-bf81-4900-a022-9443a0db6baa');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (10, 'Apt. 890 3337 Lang Path, Parkerville, NJ 80908-3185', 0, 'jinny.lynch@gmail.com', 'Owen', false, 'Sipes', NULL, 'client9', NULL, '(451) 747-9305', 'USER', '2b59de88-2242-47de-85d8-57f9a3886936');
INSERT INTO public.clients (id, address, balance, email, first_name, is_business_client, last_name, nip, password, pesel, phone_number, roles, uuid) VALUES (1, 'Suite 940 15158 Darrell Island, South Roger, MA 18928', -7198, 'melodie.rodriguez@gmail.com', 'Michelle', false, 'Hilll', NULL, 'client0', NULL, '019.091.9364 x87970', 'USER', '558804a0-b9c6-4fbf-ab7c-3164af0cdde1');


--
-- TOC entry 3417 (class 0 OID 35430)
-- Dependencies: 217
-- Data for Name: contracts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contracts (contract_id, amount, end_date, start_date, uuid, offer_entity_id) VALUES (1, 60, '2024-06-13 21:44:35.455883', '2023-06-13 21:44:35.455883', '40128262-1308-4c04-8dac-e27417d206ff', 2);
INSERT INTO public.contracts (contract_id, amount, end_date, start_date, uuid, offer_entity_id) VALUES (2, 140, '2024-06-13 21:45:56.568332', '2023-06-13 21:45:56.568332', 'f6224040-3a22-45e5-be1a-4b0b81dfd84e', 9);


--
-- TOC entry 3419 (class 0 OID 35437)
-- Dependencies: 219
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employees (id, address, contract_form, email, first_name, last_name, nip, password, pesel, phone_number, roles, salary, uuid, workplace) VALUES (1, NULL, 0, 'monica.oconnell@yahoo.com', 'Numbers', 'Walter', NULL, 'admin', NULL, '661412255', 'ADMIN', 12000, '83681545-b827-4887-be0e-5e388c97c336', 'Kielce');
INSERT INTO public.employees (id, address, contract_form, email, first_name, last_name, nip, password, pesel, phone_number, roles, salary, uuid, workplace) VALUES (2, NULL, 0, 'evelyne.rippin@hotmail.com', 'Melia', 'VonRueden', NULL, 'worker', NULL, '661412254', 'WORKER', 7000, '9d94b5e7-5624-4575-946f-cb045e99c499', 'Kielce');


--
-- TOC entry 3421 (class 0 OID 35446)
-- Dependencies: 221
-- Data for Name: notices; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3423 (class 0 OID 35453)
-- Dependencies: 223
-- Data for Name: offers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (1, 'Internet światłowodowy', false, 'Internet 300Mb/s', 0, 50, 'a6ed5d66-c5f0-4011-a2e2-72d4e2545d2b', true, 2);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (2, 'Internet światłowodowy', false, 'Internet 600Mb/s', 0, 60, '5b21bc86-49d7-48c7-93be-96e9d5d9ef9a', true, 2);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (3, 'Internet światłowodowy', false, 'Internet 1Gb/s', 0, 80, 'c5d34164-456b-4ecf-a1ca-5ad6cd3599b3', true, 2);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (4, 'Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Telewizja 50 kanałów', 1, 20, '49ed1049-3ce1-4e55-8949-d14523be1f5b', false, NULL);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (5, 'Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Telewizja 136 kanałów', 1, 40, '750ddab0-0894-4087-b140-926e0a63120c', false, NULL);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (6, 'Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Telewizja 179 kanałów', 1, 50, '20587731-92f8-4e82-b4fa-6a000aa25a32', false, NULL);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (7, 'Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Internet 300Mb/s + Telewizja 50 kanałów', 2, 85, 'ce772ea7-ba33-401e-871a-d40a6ea5501b', true, 2);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (8, 'Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Internet 600Mb/s + Telewizja 136 kanałów', 2, 110, '25a1cce1-617e-46fb-9c1f-a765b01944fb', true, 2);
INSERT INTO public.offers (id, description, is_archival, name, offer_type, price, uuid, with_device, product_entity_product_id) VALUES (9, 'Internet światłowodowy + Telewizja wciąż pozostaje głównym źródłem rozrywki i informacji zarówno dla dzieci, jak i osób dorosłych. W zależności od naszych potrzeb i preferencji w UPC możemy wybrać pakiet telewizyjny, składający się z wielu kanałów. Bez względu na wybrany Pakiet TV otrzymujemy Dekoder Horizon, który posiada m.in. funkcję odtwarzania ulubionych programów do 7 dni wstecz. Oprócz tego zyskujemy dostęp do telewizji internetowej UPC TV Go, dzięki której możemy oglądać TV zawsze i wszędzie (pod warunkiem dostępu do Internetu).', false, 'Internet 1Gb/s + Telewizja 179 kanałów', 2, 140, 'ca76a968-55d0-42f3-bb54-bee9f46540b5', true, 1);


--
-- TOC entry 3425 (class 0 OID 35462)
-- Dependencies: 225
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (order_id, amount, order_date, order_status, payment_status, uuid, id, employee_entity_id, service_service_id) VALUES (1, 7058, '2023-06-13 21:44:35.47992', 0, 0, '1f7a4082-e8df-45e5-93c9-c1bbd3b3ecb7', 1, NULL, 1);
INSERT INTO public.orders (order_id, amount, order_date, order_status, payment_status, uuid, id, employee_entity_id, service_service_id) VALUES (2, 140, '2023-06-13 21:45:56.57183', 0, 0, '5590d254-5dd4-4baa-886d-8c0c8068d7d9', 1, NULL, 2);


--
-- TOC entry 3427 (class 0 OID 35469)
-- Dependencies: 227
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.payments (payment_id, amount, data, name, payment_status, products_uuid, uuid, contract_id) VALUES (1, 7058, '2023-06-13 21:44:55.648097', 'Internet 600Mb/s', 0, '', '34b266dd-f99f-4f68-bb80-eef18b58593e', 1);
INSERT INTO public.payments (payment_id, amount, data, name, payment_status, products_uuid, uuid, contract_id) VALUES (2, 140, '2023-06-13 21:46:11.618147', 'Internet 1Gb/s + Telewizja 179 kanałów', 0, '', '58e1c40f-54cd-4ef4-bb64-f064ba7273c1', 2);


--
-- TOC entry 3429 (class 0 OID 35478)
-- Dependencies: 229
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (1, 'Ciesz się mocnym i stabilnym sygnałem Wi-Fi jak nigdy wcześniej. Deco M4 wykorzystuje technologię TP-Link Mesh, aby zapewnić silny sygnał bezprzewodowy w każdym kącie twojego domu. Jednostki Deco współpracują ze sobą, aby tworzyć jednolitą sieć Wi-Fi. Dwie jednostki Deco pokrywają siecią powierzchnię do 260m2. Połączenia bezprzewodowe i opcjonalne łącze rezerwowe Ethernet łączą jednostki Deco, zapewniając jeszcze szybszą sieć i najbardziej płynny roaming. Potrzebujesz większego zasięgu Wi-Fi? Wystarczy dodać kolejną jednostkę Deco.', 'TP-LINK Deco M4 Mesh WiFi System', 408, 'INTERNET', '093f76f7-5469-4697-b14b-adec0610299d', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (2, 'Dwupasmowa sieć w standardzie AX3000 to rozwiązanie technologiczne zastosowane w routerze TP-LINK Archer AX50. Model ten umożliwia zatem wykorzystanie obu zakresów częstotliwości połączenia z Internetem – zarówno 2,4, jak i 5 GHz. Takie połączenie gwarantuje wysoką przepustowość sieci domowej, co z kolei przekłada się na wygodę jej użytkowania. Dzięki routerowi TP-LINK Archer AX50 możesz bez przeszkód i limitów oglądać ulubione filmy, grać w gry online czy transmitować swoją rozgrywkę lub inne klipy wideo metodą przesyłania strumieniowego.', 'Router TP-LINK Archer AX50', 349.99, 'INTERNET', '8e2a864f-db48-467f-bc9b-937b965780bf', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (3, 'Sięgnij po standard Wi-Fi nowej generacji zapewniający przepustowość nawet 3000 Mb/s. ASUS RT-AX58U to dwuzakresowy router Wi-Fi 2x2 obsługujący najnowszy standard 802.11ax, zapewniając tym samym większą przepustowość i stabilność połączenia, przy podłączeniu wielu urządzeń sieciowych jednocześnie. Przy tym znacząco poprawia bezpieczeństwo Twoje i Twoich bliskich podczas korzystania z Internetu, wykorzystując w tym celu najnowsze rozwiązania na rynku.', 'Router ASUS RT-AX58U V2 Wi-Fi 6 AX3000', 538.9, 'INTERNET', '65ea113b-f950-4732-9ab5-7c153a79d1da', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (4, 'Dwupasmowy, gigabitowy router Wi‑Fi 6 AX3000 TP-Link Archer AX53 oferuje prędkości nowej generacji dochodzące do 2402 Mb/s w paśmie 5 GHz i do 574 Mb/s w paśmie 2,4 GHz, zapewniając płynną transmisję i wyższe prędkości pobierania. Napędzany przez potężny, dwurdzeniowy procesor, ten w pełni wyposażony Router Wi-Fi 6 umożliwia wykonywanie wielu zadań działających płynnie w tym samym czasie. Korzystaj ze streamingu i gier jak nigdy przedtem.', 'Router TP-LINK Archer AX53', 349, 'INTERNET', '7f730104-79a2-4d6a-ab6a-fc24b4024e30', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (5, 'Żywe kolory, kontrast, wyraziste szczegóły i głębia to cechy, które tworzą obraz doskonały. Wszystko to dostarczy Ci telewizor TCL 58P635. Rozdzielczość 4K sprawia, że ekran wyświetla ponad 8 milionów pikseli, dzięki którym zobaczysz różnicę. Model ten sprawdzi się zarówno do oglądania filmów, czy seriali, ale także do grania. Został on wyposażony w złącze HDMI 2.1 z ALLM, dzięki któremu responsywność oraz jakość obrazu będą na bardzo wysokim poziomie. To bardzo ważny parametr dla każdego gracza. Co więcej, telewizor ma bezramkową konstrukcję, dzięki której obraz wciągnie Cię jeszcze bardziej. Natomiast Smart TV z platformą Google zapewni dostęp do różnych treści dla całej rodziny.', 'TCL 58P635', 1999, 'TV', '728cc7ab-50ae-4229-9fc5-1a1c1a7f684f', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (6, '55-calowy telewizor Sony KD-55X85K został wyposażony w procesor 4K HDR Processor X1™ oraz technologie TRILUMINOS PRO™ i 4K X-Reality™ PRO, a także panel z odświeżaniem 120 Hz. Takie rozwiązanie pozwala, by urządzenie zachwyciło Cię urzekającymi kolorami dbającymi o niezwykły realizm. Ponadto telewizor zapewni Ci doskonałą rozdzielczość 4K niezależnie od źródła sygnału. Co więcej, o czysty dźwięk zadbają głośniki X-Balanced Speaker.', 'Sony KD-55X85K', 3999, 'TV', 'd9800d09-1291-45db-8622-5dbbad3ab08f', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (7, 'Xiaomi Mi LED TV A2 to 55-calowy telewizor z wyświetlaczem Premium 4K Ultra HD. Szeroka gama kolorów, wsparcie HDR10, Dolby Vision i HLG, czy technologia MEMC sprawią, że obraz będzie wyrazisty i płynny. Urządzenie ma wbudowaną łączność Wi-Fi oraz Bluetooth. Dzięki temu możesz bez przeszkód korzystać z platformy Smart TV Android. Co więcej, telewizor obsługuje dekodowanie Dolby, a także DTS-HD, FLAC, czy AAC. Dzięki temu wysokiej jakości obraz jest uzupełniony o satysfakcjonujące brzmienie. Natomiast dekoder DVB-T2 pozwoli korzystać z nowego standardu telewizji naziemnej.', 'Xiaomi Mi LED TV A2 55"', 2219, 'TV', 'b6515e20-1f7b-4d7d-8f71-b207f16a5591', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (8, 'Pozwól, by 65-calowy ekran OLED evo telewizora LG OLED65C21LA stał się Twoim, oknem na nowy, jasny świat. Urządzenie zostało wyposażone w technologię Brightness Booster, która zapewni Ci o 20% większą jasność, będąc jednocześnie ekranem przyjaznym dla oczu. Ponadto telewizor oferuje Szybki procesor α9 Gen 5 AI o niesamowite prędkości, a także wsparcie sztucznej inteligencji ThinQ AI w języku polskim. Co więcej, LG OLED65C21LA da Ci również dostęp do wielu serwisów streamingowych takich jak: Amazon Prime, Apple TV+, Disney+, HBO MAX, czy Netflix. Dodatkowo dzięki technologiom Dolby Vision IQ oraz Dolby Atmos zastosowanym w telewizorze będziesz mógł cieszyć się prawdziwie kinowymi wrażeniami w Twoim salonie.', 'LG OLED65C21LA', 7990, 'TV', 'd64bec74-c941-4398-843b-db023badc1de', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (9, 'Samsung Galaxy S23 to smartfon kompletny. Szczególnie spodoba się użytkownikom, którzy preferują telefony bez problemu mieszczące się w kieszeni i jednocześnie potrzebują sprzętu z górnej półki. Wysoka wydajność, ogromne możliwości fotograficzne, świetny ekran, dobry czas pracy baterii i ładny design – to cechy, które najbardziej wyróżniają Galaxy S23.', 'Samsung Galaxy S23 8/256GB', 4799, 'MOBILE', '5a22215d-3cd6-4bb8-9e37-4d9a089a5414', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (10, 'Sprawdź, co może zaoferować Ci Motorola edge 40 5G 256 GB Coronet Blue. Elegancka obudowa pewnie leży w dłoni i świetnie komponuje się z każdym stylem. Nie obawiaj się przypadkowego zachlapania wodą. Klasa odporności IP68 dba, aby woda nie dostała się do wnętrza smartfona. Rób piękne i ciekawy zdjęcia, które zapiszą Twoje wspomnienia w wysokiej rozdzielczości.', 'Motorola edge 40 5G 8/256GB', 2999, 'MOBILE', '0451095c-9bff-4af7-8759-7f039d461246', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (11, 'Sprawdź, co to znaczy zabawa bez ograniczeń. Odkrywaj wirtualny świat razem z Samsung Galaxy M33 5G 128 GB Green. Baw się, graj, oglądaj filmy i słuchaj muzyki z wydajnym smartfonem, którego bateria wystarczy Ci na cały dzień wrażeń. Twórz wspomnienia z pomocą telefonu i zapisuj je w najlepszej jakości, aby wracając do nich, poczuć się znów, jak na wakacjach. Czerp wiele radości z wyświetlanym treści na dużym ekranie z wysoką częstotliwością odświeżania, która nie pozwoli na żadne opóźnienia.', 'Samsung Galaxy M33 5G 6/128', 1099, 'MOBILE', '1085f125-83d8-423b-9971-8517a031363f', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (12, 'Poznaj nową odsłonę serii smartfonów, która zrodziła się z piękna. Przed Tobą Huawei P60 Pro 256 GB czarny. Zapewne już wiesz, czego możesz spodziewać się po tym modelu. To wyjątkowa dbałość o jakość wykonania oraz najlepsze możliwości fotograficzne.Nowe aparaty XMAGE i obiektyw w regulowaną przysłoną pozwoli Ci poczuć się jak prawdziwy profesjonalista. Rób niesamowite zdjęcia w każdych warunkach i podziwiaj je na przestronny ekranie OLED o rozmiarze 6,67”. Moc do działania dostarczy Ci nowoczesny procesor, sporo pamięci RAM oraz wytrzymała bateria o sporej pojemności. Wybierz Huawei P60 Pro i poznaj jakość premium.', 'Huawei P60 Pro 8/256GB', 5499, 'MOBILE', 'ec81ffde-ac36-497c-913f-9e6558f422ce', NULL);
INSERT INTO public.products (product_id, description, name, price, product_type, uuid, order_id) VALUES (13, 'Odkrywaj wirtualny świat razem z Xiaomi POCO X5 Pro 5G 256 GB Black i ciesz się jakością, jaką oferuje. Solidnie wykonana konstrukcja sprawdzi się doskonale podczas intensywnych i pełnych wrażeń dni. Poza tym smartfon dysponuje pojemną baterią, więc możesz korzystać z niego cały dzień. Dziel się treściami z przyjaciółmi, graj w nowe mobilne tytuły i prowadź długie rozmowy telefoniczne z bliskimi. Rób świetnej jakości zdjęcia i ciesz się z wyraźnym wspomnień wakacyjnego szaleństwa.', 'Xiaomi POCO X5 Pro 5G 8/256GB', 1799, 'MOBILE', '81cca2f4-3e2a-4502-8000-6c7e3289e401', NULL);


--
-- TOC entry 3431 (class 0 OID 35487)
-- Dependencies: 231
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.services (service_id, name, offer_type, uuid, id, contract_id) VALUES (1, 'Internet 600Mb/s', 0, 'b7e95fb2-abf2-4c7d-877b-ca4cea12b214', 1, 1);
INSERT INTO public.services (service_id, name, offer_type, uuid, id, contract_id) VALUES (2, 'Internet 1Gb/s + Telewizja 179 kanałów', 2, '256e626f-3536-4eff-b9c3-02acdffee790', 1, 2);


--
-- TOC entry 3433 (class 0 OID 35494)
-- Dependencies: 233
-- Data for Name: user_problems; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3435 (class 0 OID 35503)
-- Dependencies: 235
-- Data for Name: warehouses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (1, 'TP-LINK Deco M4 Mesh WiFi System', 4, '093f76f7-5469-4697-b14b-adec0610299d', 1);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (2, 'Router TP-LINK Archer AX50', 0, '8e2a864f-db48-467f-bc9b-937b965780bf', 2);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (3, 'Router ASUS RT-AX58U V2 Wi-Fi 6 AX3000', 14, '65ea113b-f950-4732-9ab5-7c153a79d1da', 3);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (4, 'Router TP-LINK Archer AX53', 15, '7f730104-79a2-4d6a-ab6a-fc24b4024e30', 4);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (5, 'TCL 58P635', 12, '728cc7ab-50ae-4229-9fc5-1a1c1a7f684f', 5);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (6, 'Sony KD-55X85K', 12, 'd9800d09-1291-45db-8622-5dbbad3ab08f', 6);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (7, 'Xiaomi Mi LED TV A2 55"', 6, 'b6515e20-1f7b-4d7d-8f71-b207f16a5591', 7);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (8, 'LG OLED65C21LA', 17, 'd64bec74-c941-4398-843b-db023badc1de', 8);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (9, 'Samsung Galaxy S23 8/256GB', 25, '5a22215d-3cd6-4bb8-9e37-4d9a089a5414', 9);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (10, 'Motorola edge 40 5G 8/256GB', 3, '0451095c-9bff-4af7-8759-7f039d461246', 10);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (11, 'Samsung Galaxy M33 5G 6/128', 11, '1085f125-83d8-423b-9971-8517a031363f', 11);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (12, 'Huawei P60 Pro 8/256GB', 26, 'ec81ffde-ac36-497c-913f-9e6558f422ce', 12);
INSERT INTO public.warehouses (id, product_name, quantity, uuid, product_entity_product_id) VALUES (13, 'Xiaomi POCO X5 Pro 5G 8/256GB', 14, '81cca2f4-3e2a-4502-8000-6c7e3289e401', 13);


--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 214
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_id_seq', 10, true);


--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 216
-- Name: contracts_contract_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contracts_contract_id_seq', 2, true);


--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 218
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employees_id_seq', 2, true);


--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 220
-- Name: notices_notice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notices_notice_id_seq', 1, false);


--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 222
-- Name: offers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.offers_id_seq', 9, true);


--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 2, true);


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 226
-- Name: payments_payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payments_payment_id_seq', 2, true);


--
-- TOC entry 3459 (class 0 OID 0)
-- Dependencies: 228
-- Name: products_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_product_id_seq', 13, true);


--
-- TOC entry 3460 (class 0 OID 0)
-- Dependencies: 230
-- Name: services_service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.services_service_id_seq', 2, true);


--
-- TOC entry 3461 (class 0 OID 0)
-- Dependencies: 232
-- Name: user_problems_user_problem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_problems_user_problem_id_seq', 1, false);


--
-- TOC entry 3462 (class 0 OID 0)
-- Dependencies: 234
-- Name: warehouses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.warehouses_id_seq', 13, true);


--
-- TOC entry 3235 (class 2606 OID 35428)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 3239 (class 2606 OID 35435)
-- Name: contracts contracts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT contracts_pkey PRIMARY KEY (contract_id);


--
-- TOC entry 3241 (class 2606 OID 35444)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);


--
-- TOC entry 3245 (class 2606 OID 35451)
-- Name: notices notices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT notices_pkey PRIMARY KEY (notice_id);


--
-- TOC entry 3247 (class 2606 OID 35460)
-- Name: offers offers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT offers_pkey PRIMARY KEY (id);


--
-- TOC entry 3249 (class 2606 OID 35467)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 3251 (class 2606 OID 35476)
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);


--
-- TOC entry 3253 (class 2606 OID 35485)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- TOC entry 3255 (class 2606 OID 35492)
-- Name: services services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_pkey PRIMARY KEY (service_id);


--
-- TOC entry 3243 (class 2606 OID 35512)
-- Name: employees uk_j9xgmd0ya5jmus09o0b8pqrpb; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT uk_j9xgmd0ya5jmus09o0b8pqrpb UNIQUE (email);


--
-- TOC entry 3237 (class 2606 OID 35510)
-- Name: clients uk_srv16ica2c1csub334bxjjb59; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT uk_srv16ica2c1csub334bxjjb59 UNIQUE (email);


--
-- TOC entry 3257 (class 2606 OID 35501)
-- Name: user_problems user_problems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_problems
    ADD CONSTRAINT user_problems_pkey PRIMARY KEY (user_problem_id);


--
-- TOC entry 3259 (class 2606 OID 35508)
-- Name: warehouses warehouses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouses
    ADD CONSTRAINT warehouses_pkey PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 35513)
-- Name: contracts fk59akclxqdt8wqwewlmfxqqpqe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contracts
    ADD CONSTRAINT fk59akclxqdt8wqwewlmfxqqpqe FOREIGN KEY (offer_entity_id) REFERENCES public.offers(id);


--
-- TOC entry 3271 (class 2606 OID 35568)
-- Name: warehouses fk7guygs8v0g9thpqkxvqiy1hja; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouses
    ADD CONSTRAINT fk7guygs8v0g9thpqkxvqiy1hja FOREIGN KEY (product_entity_product_id) REFERENCES public.products(product_id);


--
-- TOC entry 3263 (class 2606 OID 35538)
-- Name: orders fk840b0ihty8egeps54dslxxpm0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk840b0ihty8egeps54dslxxpm0 FOREIGN KEY (service_service_id) REFERENCES public.services(service_id);


--
-- TOC entry 3268 (class 2606 OID 35553)
-- Name: services fkaqn83tv87vhytdu1w8cb5ctqt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT fkaqn83tv87vhytdu1w8cb5ctqt FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3269 (class 2606 OID 35558)
-- Name: services fkioxlygx0qp4x0yf96pc981gv6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT fkioxlygx0qp4x0yf96pc981gv6 FOREIGN KEY (contract_id) REFERENCES public.contracts(contract_id);


--
-- TOC entry 3261 (class 2606 OID 35518)
-- Name: notices fkjfgspyc1k9b8hlfp9mcfy4kfe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notices
    ADD CONSTRAINT fkjfgspyc1k9b8hlfp9mcfy4kfe FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3264 (class 2606 OID 35528)
-- Name: orders fkk1ri6s69qqq84rdxoc8556310; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkk1ri6s69qqq84rdxoc8556310 FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3270 (class 2606 OID 35563)
-- Name: user_problems fknc3agf56q54yc1ekyypsccqxm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_problems
    ADD CONSTRAINT fknc3agf56q54yc1ekyypsccqxm FOREIGN KEY (id) REFERENCES public.clients(id);


--
-- TOC entry 3262 (class 2606 OID 35523)
-- Name: offers fkp6ugshsymg2knhiy9sseuk0w8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offers
    ADD CONSTRAINT fkp6ugshsymg2knhiy9sseuk0w8 FOREIGN KEY (product_entity_product_id) REFERENCES public.products(product_id);


--
-- TOC entry 3266 (class 2606 OID 35543)
-- Name: payments fkqywegtqyijw241foqfkseq1l6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT fkqywegtqyijw241foqfkseq1l6 FOREIGN KEY (contract_id) REFERENCES public.contracts(contract_id);


--
-- TOC entry 3267 (class 2606 OID 35548)
-- Name: products fkr3aftk48ylvntpui7l04kbcc1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkr3aftk48ylvntpui7l04kbcc1 FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 3265 (class 2606 OID 35533)
-- Name: orders fkrk0sc89d5h7e9rnjkjxwj95gf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkrk0sc89d5h7e9rnjkjxwj95gf FOREIGN KEY (employee_entity_id) REFERENCES public.employees(id);


-- Completed on 2023-06-13 21:47:25

--
-- PostgreSQL database dump complete
--

