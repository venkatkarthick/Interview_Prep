'use strict';
const {
  Document, Packer, Paragraph, TextRun, Table, TableRow, TableCell,
  ImageRun, Header, Footer, HeadingLevel, AlignmentType, BorderStyle,
  WidthType, ShadingType, LevelFormat, PageBreak, VerticalAlign,
  TabStopType, TabStopPosition, PositionalTab, PositionalTabAlignment,
  PositionalTabRelativeTo, PositionalTabLeader
} = require('docx');
const fs = require('fs');

// ─── Palette ──────────────────────────────────────────────────────────────────
const P = {
  navy:'0F2D5A', blue:'1D4ED8', teal:'0E7490', green:'065F46', purple:'5B21B6',
  amber:'92400E', red:'991B1B', pink:'9D174D',
  // light fills
  lBlue:'DBEAFE', lGreen:'D1FAE5', lAmber:'FEF3C7', lRed:'FEE2E2',
  lPurple:'EDE9FE', lTeal:'CFFAFE', lGray:'F1F5F9',
  // code
  codeBg:'1E293B', codeText:'E2E8F0',
  // text
  body:'1E293B', light:'64748B', white:'FFFFFF',
};

// ─── Helpers ─────────────────────────────────────────────────────────────────
const imgRun = (file, w, h) => {
  const data = fs.readFileSync(`/home/claude/${file}`);
  return new ImageRun({ data, type:'png', transformation:{width:w,height:h},
    altText:{title:file,description:file,name:file} });
};

const imgPara = (file, w, h, align=AlignmentType.CENTER) =>
  new Paragraph({ alignment:align, spacing:{before:140,after:140},
    children:[imgRun(file,w,h)] });

const pb = () => new Paragraph({ children:[new PageBreak()] });
const sp = (pt=100) => new Paragraph({ children:[], spacing:{before:0,after:pt} });

// Heading helpers
const h1 = (txt, color=P.navy) => new Paragraph({
  heading: HeadingLevel.HEADING_1,
  spacing: {before:440,after:180},
  border: { bottom:{style:BorderStyle.THICK,size:6,color:color} },
  children:[new TextRun({text:txt,bold:true,size:44,color,font:'Calibri'})]
});
const h2 = (txt, color=P.blue) => new Paragraph({
  heading: HeadingLevel.HEADING_2,
  spacing: {before:320,after:120},
  children:[new TextRun({text:txt,bold:true,size:34,color,font:'Calibri'})]
});
const h3 = (txt, color=P.teal) => new Paragraph({
  heading: HeadingLevel.HEADING_3,
  spacing: {before:220,after:80},
  children:[new TextRun({text:txt,bold:true,size:26,color,font:'Calibri'})]
});

// Body text
const body = (txt, opts={}) => new Paragraph({
  spacing:{before:70,after:70},
  children:[new TextRun({text:txt,size:22,font:'Calibri',color:P.body,...opts})]
});
const bold = (txt,col=P.navy) => body(txt,{bold:true,color:col});

// Bullet (uses numbering)
const bul = (txt, level=0) => new Paragraph({
  numbering:{reference:'bullets',level},
  spacing:{before:50,after:50},
  children:[new TextRun({text:txt,size:22,font:'Calibri',color:P.body})]
});

// Numbered list
const num = (txt, ref='numbers') => new Paragraph({
  numbering:{reference:ref,level:0},
  spacing:{before:50,after:50},
  children:[new TextRun({text:txt,size:22,font:'Calibri',color:P.body})]
});

// Code block
const code = (lines) => {
  const arr = [];
  arr.push(new Paragraph({
    spacing:{before:100,after:0},
    shading:{fill:P.codeBg,type:ShadingType.CLEAR},
    border:{left:{style:BorderStyle.THICK,size:8,color:P.teal}},
    indent:{left:360},
    children:[new TextRun({text:'',size:18})]
  }));
  for(const line of lines){
    arr.push(new Paragraph({
      spacing:{before:0,after:0},
      shading:{fill:P.codeBg,type:ShadingType.CLEAR},
      indent:{left:520},
      children:[new TextRun({text:line,size:18,font:'Courier New',color:P.codeText})]
    }));
  }
  arr.push(new Paragraph({
    spacing:{before:0,after:120},
    shading:{fill:P.codeBg,type:ShadingType.CLEAR},
    children:[new TextRun({text:'',size:18})]
  }));
  return arr;
};

// Callout / info box
const callout = (txt, fill, border) => new Paragraph({
  spacing:{before:100,after:100},
  shading:{fill,type:ShadingType.CLEAR},
  border:{left:{style:BorderStyle.THICK,size:10,color:border}},
  indent:{left:360,right:360},
  children:[new TextRun({text:txt,size:22,font:'Calibri',color:P.body})]
});
const info = (txt)  => callout(`ℹ️  ${txt}`, P.lBlue,  P.blue);
const warn = (txt)  => callout(`⚠️  ${txt}`, P.lAmber, '92400E');
const tip  = (txt)  => callout(`💡  ${txt}`, P.lGreen, P.green);
const key  = (txt)  => callout(`🔑  ${txt}`, P.lPurple,P.purple);

// Table helper
const W9360 = 9360;
const tbl = (headers, rows, widths) => {
  const total = widths.reduce((a,b)=>a+b,0);
  const brd = {style:BorderStyle.SINGLE,size:1,color:'CBD5E1'};
  const borders = {top:brd,bottom:brd,left:brd,right:brd};
  const hColors = [P.navy,P.blue,P.teal,P.green,P.purple,'92400E'];
  return new Table({
    width:{size:total,type:WidthType.DXA},
    columnWidths:widths,
    rows:[
      new TableRow({children:headers.map((h,i)=>new TableCell({
        borders, width:{size:widths[i],type:WidthType.DXA},
        shading:{fill:hColors[i%hColors.length],type:ShadingType.CLEAR},
        margins:{top:80,bottom:80,left:140,right:140},
        children:[new Paragraph({alignment:AlignmentType.CENTER,
          children:[new TextRun({text:h,bold:true,color:P.white,size:21,font:'Calibri'})]})]
      }))
      }),
      ...rows.map((row,ri)=>new TableRow({children:row.map((cell,ci)=>new TableCell({
        borders, width:{size:widths[ci],type:WidthType.DXA},
        shading:{fill:ri%2===0?'F8FAFC':P.lGray,type:ShadingType.CLEAR},
        margins:{top:70,bottom:70,left:140,right:140},
        children:[new Paragraph({
          children:[new TextRun({text:cell,size:20,font:'Calibri',color:P.body})]
        })]
      }))}))
    ]
  });
};

// ─── Cover Page ───────────────────────────────────────────────────────────────
const cover = () => [
  sp(1800),
  new Paragraph({
    alignment:AlignmentType.CENTER,
    spacing:{before:0,after:200},
    children:[new TextRun({text:'High-Level Design',bold:true,size:80,color:P.navy,font:'Calibri'})]
  }),
  new Paragraph({
    alignment:AlignmentType.CENTER,
    spacing:{before:0,after:140},
    children:[new TextRun({text:'Complete Series Notes — Set 1',size:40,color:P.blue,font:'Calibri',italics:true})]
  }),
  new Paragraph({
    alignment:AlignmentType.CENTER,
    spacing:{before:0,after:200},
    border:{bottom:{style:BorderStyle.SINGLE,size:4,color:'CBD5E1',space:1}},
    children:[new TextRun({text:'',size:24})]
  }),
  sp(80),
  new Paragraph({
    alignment:AlignmentType.CENTER,
    spacing:{before:0,after:60},
    children:[new TextRun({text:'Videos Covered in This Document',bold:true,size:26,color:P.body,font:'Calibri'})]
  }),
  ...[
    '📡  Video 1  —  Network Protocols (HTTP, WebSocket, WebRTC, TCP, UDP)',
    '⚖️  Video 2  —  CAP Theorem Deep Dive',
    '🏗️  Video 3  —  Monolithic vs Microservices Architecture',
    '🔧  Video 4  —  Microservices Design Patterns (Strangler Fig, Saga, CQRS)',
    '🚀  Video 5  —  Scaling from 0 to 1 Million Users',
  ].map(t=>new Paragraph({
    alignment:AlignmentType.CENTER,
    spacing:{before:60,after:60},
    children:[new TextRun({text:t,size:24,font:'Calibri',color:P.body})]
  })),
  sp(600),
  new Paragraph({
    alignment:AlignmentType.CENTER,
    children:[new TextRun({text:'Concept Coding • HLD Series',size:22,font:'Calibri',color:P.light,italics:true})]
  }),
  pb(),
];

// ─── VIDEO 1 — Network Protocols ─────────────────────────────────────────────
const v1 = () => [
  h1('📡  Video 1: Network Protocols'),
  body('The foundation of every system design interview. Knowing which protocol to pick — and more importantly why — separates a good design from a great one. This video sets the stage for all HLD discussions.'),
  sp(60),

  h2('Why Network Protocols Matter in HLD'),
  body('A network protocol is a set of rules that govern how two computers communicate over a network. Think of it as the shared language two systems must agree on before exchanging data. Without protocols, no two systems could reliably talk to each other.'),
  info('Designing WhatsApp? → Use WebSocket.  Designing Google Meet? → Use WebRTC + UDP.  Building a REST API? → HTTP/HTTPS. Knowing the right protocol is the first decision.'),
  sp(),

  h2('The OSI Model — Your Mental Framework'),
  body('You learned this in college. In HLD we care about only two layers out of seven:'),
  bul('Layer 7 (Application): Where HTTP, WebSocket, WebRTC, FTP, SMTP live'),
  bul('Layer 4 (Transport): Where TCP and UDP live'),
  imgPara('osi.png', 580, 320),

  h2('Application Layer Protocols'),
  h3('Client–Server vs Peer-to-Peer'),
  body('The application layer splits into two fundamental models:'),
  imgPara('cs_p2p.png', 640, 281),
  sp(80),

  h3('Client–Server Protocols'),
  body('In the client–server model, the client always initiates; the server always responds. Communication is inherently one-way initiated. The server never proactively contacts a client (except in WebSocket).'),
  tbl(
    ['Protocol','Full Name','Primary Use','Key Characteristic'],
    [
      ['HTTP','HyperText Transfer Protocol','Web pages, REST APIs','Request → Response, stateless'],
      ['HTTPS','HTTP + TLS','Secure web communication','Encrypted HTTP'],
      ['FTP','File Transfer Protocol','File transfers','Two connections: control + data. DATA NOT ENCRYPTED — avoid!'],
      ['SMTP','Simple Mail Transfer Protocol','Sending email','Works with IMAP/POP3 for receiving'],
      ['IMAP','Internet Message Access Protocol','Reading email (multi-device)','Emails stay on server — read from any device'],
      ['POP3','Post Office Protocol v3','Reading email (old)','Downloads & deletes — single device only, outdated'],
    ],
    [1400,2200,1900,3860]
  ),
  sp(120),
  warn('FTP is insecure — the data connection is not encrypted. Never use FTP in production. Use HTTPS or SFTP instead.'),
  sp(),

  h3('WebSocket — Bidirectional but NOT Peer-to-Peer'),
  body('WebSocket is a client–server protocol — but unlike HTTP, the connection stays alive and both sides can send messages at any time. This is bidirectional persistent communication.'),
  body('Common misconception: WebSocket is NOT peer-to-peer. The server still sits in the middle. Client 1 and Client 2 cannot talk directly — they both talk to the server, which forwards messages.'),
  ...code([
    '// HTTP flow (request–response, connection closes)',
    'Client → Server: GET /messages',
    'Server → Client: [response data] ← connection closes',
    '',
    '// WebSocket flow (persistent, bidirectional)',
    'Client → Server: HTTP Upgrade request',
    'Server → Client: 101 Switching Protocols',
    '-- persistent connection established --',
    'Client → Server: { "type": "message", "text": "Hey!" }',
    'Server → Client: { "type": "message", "text": "Hi back!" }',
    'Server → Client: { "type": "notification", "text": "User X joined" }',
    '-- server can push WITHOUT client asking --',
  ]),
  tip('Use WebSocket whenever you need the server to push data to the client without the client polling. Classic example: WhatsApp — when a new message arrives at the server, it must immediately push it to your device.'),
  sp(),

  h3('Peer-to-Peer & WebRTC'),
  body('In true P2P, every node can be both client and server. Nodes communicate directly with each other — no server intermediary for the actual data. This is what makes WebRTC fast.'),
  body('WebRTC (Web Real-Time Communication) enables browser-to-browser direct communication. Used in Google Meet, Zoom, and any video/audio calling application. No server hop means lower latency.'),
  key('WebRTC operates at the application layer but uses UDP at the transport layer — giving it both P2P architecture AND UDP speed. Perfect for real-time media.'),
  sp(),

  h2('Transport Layer: TCP vs UDP'),
  body('This is where the "how data actually travels" decision is made. Two options — one reliable, one fast.'),
  imgPara('tcp_udp.png', 600, 267),
  sp(80),

  h3('TCP — Transmission Control Protocol'),
  body('TCP guarantees delivery. Every packet is acknowledged. Lost packets are retransmitted. Packets arrive in order. A virtual connection is maintained throughout the session.'),
  ...code([
    '// TCP 3-Way Handshake before any data flows:',
    'Client → Server: SYN          (I want to connect)',
    'Server → Client: SYN-ACK     (Acknowledged, ready)',
    'Client → Server: ACK          (Confirmed, let\'s go)',
    '',
    '// Data transfer with ordering:',
    'Client sends: [Packet 1] [Packet 2] [Packet 3]',
    'Network reorders: Server receives [Packet 2] [Packet 1] [Packet 3]',
    'TCP layer reorders them back: [Packet 1] [Packet 2] [Packet 3]',
    '',
    '// If Packet 3 is lost:',
    'Server: ACK for 1, ACK for 2 ... no ACK for 3',
    'Client: Retransmit Packet 3',
  ]),

  h3('UDP — User Datagram Protocol'),
  body('UDP throws away guarantees for speed. No connection setup, no acknowledgements, no ordering, no retransmission. Just send and forget. The price is that packets may be lost or arrive out of order. The gain is minimal latency.'),
  body('When is packet loss acceptable? When you are streaming live video or audio. If you miss one frame in a video call, you do not want the call to pause and replay it — you just skip that frame and continue. Speed matters more than completeness.'),
  tip('WebRTC uses UDP under the hood. P2P + UDP = maximum speed for real-time media.'),
  sp(),

  h2('Protocol Decision Guide — Quick Reference'),
  tbl(
    ['Scenario / Requirement','Pick This','Why'],
    [
      ['Regular web page, REST API','HTTP/HTTPS','Standard stateless request-response'],
      ['Design WhatsApp / Telegram / Slack','WebSocket','Server must push messages to clients'],
      ['Design Google Meet / Zoom (video calling)','WebRTC + UDP','P2P speed, packet loss acceptable for media'],
      ['Live video streaming (YouTube Live)','UDP','Latency critical, missing frames are fine'],
      ['File upload/download (secure)','HTTPS','Encrypted, reliable, ordered'],
      ['Email sending system','SMTP','Standard email protocol'],
      ['Email reading (multi-device)','IMAP','Server-side storage, sync across devices'],
    ],
    [2800,2000,4760]
  ),
  sp(),

  h2('Key Interview Takeaways'),
  bul('HTTP = stateless request-response; always client-initiates'),
  bul('WebSocket = persistent bidirectional; server can push; still client-server (NOT P2P)'),
  bul('WebRTC = true P2P; no server hop for data; uses UDP underneath'),
  bul('TCP = reliable, ordered, acknowledged — use when data integrity matters'),
  bul('UDP = fast, no guarantees — use for real-time streaming where speed > reliability'),
  bul('FTP is insecure (unencrypted data channel) — always use HTTPS/SFTP in production'),
  pb(),
];

// ─── VIDEO 2 — CAP Theorem ────────────────────────────────────────────────────
const v2 = () => [
  h1('⚖️  Video 2: CAP Theorem'),
  body('One of the most commonly asked HLD topics in senior-level interviews. Understanding CAP is not just theory — it directly shapes every distributed system design decision. Get it right early or re-architect everything later.'),
  sp(60),

  h2('What is CAP Theorem?'),
  key('CAP Theorem defines the three desirable properties of a distributed system with replicated data — and proves you can achieve at most two of them simultaneously.'),
  sp(80),
  body('Before diving in, let\'s establish what "distributed system with replicated data" means:'),
  bul('You have multiple database nodes (e.g., Node B in India, Node C in USA)'),
  bul('Both nodes hold the same data — B replicates to C and vice versa'),
  bul('Your application can read/write to either node'),
  bul('Users don\'t know which node they\'re hitting — they just query "the system"'),
  sp(),
  imgPara('cap.png', 540, 420),
  sp(80),

  h2('The Three Properties Explained'),

  h3('C — Consistency'),
  body('After any successful write to any node, every subsequent read from any node must return that exact same value. Every node returns the most recent data at all times.'),
  ...code([
    '// Node B has: a = 4',
    '// Write: a = 5 to Node B',
    '// Node B replicates → Node C also updates to 5',
    '',
    '// Consistency means:',
    'Read from B → returns 5  ✓',
    'Read from C → returns 5  ✓',
    '',
    '// Inconsistency would be:',
    'Read from B → returns 5',
    'Read from C → returns 4 (stale) ✗',
  ]),

  h3('A — Availability'),
  body('Every request sent to the system must receive a response — whether success or failure. The system must never hang indefinitely or return no response at all. All nodes must respond.'),
  body('Note: Availability does NOT guarantee the response is fresh. It just guarantees you get one.'),

  h3('P — Partition Tolerance'),
  body('This is the most misunderstood property. A "partition" means the network link between nodes breaks — Nodes B and C can\'t communicate with each other to replicate data. Partition tolerance means the system continues to function and serve requests even when this happens.'),
  info('Network partitions happen constantly in real distributed systems. A cable gets cut, a data center loses internet, a switch fails for 5 minutes. Partition tolerance is non-negotiable in practice.'),
  sp(),

  h2('Why You Can\'t Have All Three'),
  body('Let\'s trace through all three combinations with a real scenario:'),
  sp(80),

  h3('Scenario: Partition Occurs (B and C can\'t sync)'),
  tbl(
    ['Combination','What You Keep','What You Sacrifice','What Happens During Partition','Real-World Example'],
    [
      ['CP','Consistency + Partition Tolerance','Availability','Take one node offline (C). All reads/writes go to B only. Data is always consistent but C is unavailable.','ZooKeeper, HBase, Etcd'],
      ['AP','Availability + Partition Tolerance','Consistency','Both nodes stay up. B has a=6, C has stale a=5. Both respond but data diverges.','Cassandra, DynamoDB, CouchDB'],
      ['CA','Consistency + Availability','Partition Tolerance','System must stop if partition occurs — can\'t stay consistent AND available with a split. System goes down.','Single-node PostgreSQL (no real distribution)'],
    ],
    [1200,2200,2000,2600,1360]
  ),
  sp(120),

  h3('Walking Through CP in Detail'),
  body('You want consistency + partition tolerance. A partition occurs. What do you do?'),
  ...code([
    '// Before partition:',
    'Node B: a = 5   Node C: a = 5  (both in sync)',
    '',
    '// Partition occurs — B and C can\'t talk to each other',
    '// Write: a = 6 arrives at Node B',
    'Node B: a = 6',
    'Node B wants to replicate to C, but PARTITION blocks it.',
    '',
    '// To maintain Consistency:',
    'Solution → Take Node C DOWN (unavailable).',
    'Now all requests route to B only.',
    'Read from B → always returns 6  ✓ CONSISTENT',
    'Node C is offline → ✗ NOT FULLY AVAILABLE',
    '',
    '// Result: CP achieved. Availability sacrificed.',
  ]),

  h3('Walking Through AP in Detail'),
  body('You want availability + partition tolerance. A partition occurs. What do you do?'),
  ...code([
    '// Partition occurs. Both B and C stay ONLINE.',
    '// Write: a = 6 arrives at Node B',
    'Node B: a = 6  (updated)',
    'Node C: a = 5  (stale — can\'t sync due to partition)',
    '',
    '// User queries B → gets 6',
    '// User queries C → gets 5  (stale data!)',
    '',
    '// Both nodes respond → ✓ AVAILABLE',
    '// Data differs across nodes → ✗ NOT CONSISTENT',
    '',
    '// Result: AP achieved. Consistency sacrificed.',
  ]),
  sp(),

  h2('The Golden Rule for Interviews'),
  warn('In the real world, Partition Tolerance is ALWAYS required. Network failures are a fact of life. Never design a distributed system that goes down every time there is a network hiccup. Always keep P.'),
  sp(80),
  body('Since P is always required, your real choice is:'),
  bul('CP — Choose when data correctness is critical (banking, financial systems, inventory)'),
  bul('AP — Choose when availability is critical and slight staleness is acceptable (social media, DNS, shopping cart)'),
  sp(80),
  tbl(
    ['System Type','CAP Choice','Reasoning'],
    [
      ['Banking / Payments','CP','Wrong balance shown → financial disaster. Take downtime, not wrong data.'],
      ['Social media likes counter','AP','Showing 1000 instead of 1001 likes is fine. App must stay available.'],
      ['Shopping cart','AP','Cart temporarily showing old items is acceptable. Service outage is not.'],
      ['Distributed lock manager','CP','Lock must be correct. Wrong lock → race conditions and data corruption.'],
      ['DNS lookup','AP','Slightly stale DNS is fine. DNS being down is catastrophic.'],
    ],
    [2200,1400,5960]
  ),
  sp(),
  pb(),
];

// ─── VIDEO 3 — Monolith vs Microservices ─────────────────────────────────────
const v3 = () => [
  h1('🏗️  Video 3: Monolithic vs Microservices'),
  body('The instructor calls this out directly: "Around 50 interview questions in HLD can come from this single topic alone." Microservices design patterns are central to every senior engineering role.'),
  sp(60),

  h2('Monolithic Architecture'),
  body('A monolithic application is one giant codebase where every feature — authentication, orders, inventory, billing, payments, notifications — lives in a single deployable unit.'),
  imgPara('mono_micro.png', 660, 368),
  sp(80),
  info('When companies say "we have a legacy application we need to migrate to microservices" — they are talking about migrating from a monolith.'),
  sp(),

  h3('Why Monoliths Were Used'),
  body('Monoliths are simple to build when starting out. One codebase, one deployment, one database. Perfect for a small team building a small product. The problems emerge at scale.'),

  h3('Disadvantages of Monolith'),
  tbl(
    ['Problem','What Happens','Impact'],
    [
      ['Overloaded IDE','The codebase grows to GBs. Opening it in IntelliJ/Eclipse takes minutes or crashes the IDE.','Developer productivity destroyed'],
      ['Tight Coupling','All code shares the same runtime. One-line bug fix? Must test + redeploy the entire application.','Every change is high risk'],
      ['Slow CI/CD','Full regression test suite runs for every change, no matter how small.','Deployment takes hours, developers block each other'],
      ['Hard to Scale','Need more capacity for Order processing only? You must scale the ENTIRE 10GB app just for that one feature.','Expensive and inefficient'],
      ['No tech flexibility','All modules must use the same language, framework, and database.','Can\'t adopt new technologies'],
    ],
    [1800,3200,4360]
  ),
  sp(),

  h2('Microservices Architecture'),
  body('Break the monolith into small, independently deployable services. Each service owns exactly one business capability and runs in its own process with its own database.'),

  h3('Advantages of Microservices'),
  body('Every disadvantage of monolith becomes an advantage:'),
  bul('Small codebases — easy to understand, modify, and load in your IDE'),
  bul('Independent deployment — deploy the Order service without touching the Payment service'),
  bul('Granular scaling — only scale the service under load. Cost-efficient.'),
  bul('Technology freedom — Order service uses Java, ML service uses Python, Search uses Elasticsearch'),
  bul('Team ownership — each team owns their service end-to-end, faster iteration'),
  bul('Fault isolation — one service crashing does not bring down the whole system'),
  sp(),

  h3('Disadvantages of Microservices'),
  body('Microservices are not free. They introduce new complexity:'),
  bul('Proper decomposition is hard — poorly split services = high inter-service latency + tight coupling'),
  bul('Debugging is harder — an error in Service A might originate from Service C (distributed tracing needed)'),
  bul('Transaction management — with separate databases, ACID transactions across services are no longer trivial'),
  bul('Service contract fragility — if Service 3 changes its API, all its callers break simultaneously'),
  sp(),

  h3('Transaction Management Problem (Critical Interview Topic)'),
  body('This is the most important disadvantage. With one shared database, wrapping 10 table updates in a single ACID transaction is trivial. With separate databases per service, there is no single transaction boundary.'),
  ...code([
    '// Monolith — single ACID transaction:',
    'BEGIN TRANSACTION',
    '  INSERT INTO orders ...         -- success',
    '  UPDATE inventory ...           -- success',
    '  INSERT INTO payments ...       -- FAILS',
    'ROLLBACK  -- all changes undone cleanly',
    '',
    '// Microservices with separate DBs — no shared transaction:',
    'Order Service: INSERT into orders_db   -- success ✓',
    'Inventory Service: UPDATE inventory_db  -- success ✓',
    'Payment Service: INSERT into payments_db -- FAILS ✗',
    '// Now what? Orders and inventory are updated but payment failed.',
    '// How do you roll back across separate databases?',
    '// → This is solved by the SAGA Pattern (Video 4)',
  ]),
  sp(),

  h2('How Small is "Micro"?'),
  body('No fixed definition. "Micro" is relative to the size of your total system. An Order Management service can be a large standalone application and still be considered a "microservice" in the context of a large e-commerce platform.'),
  tip('Rule of thumb: a service should do one thing and do it well. If you can describe it in a single sentence ("handles all order placement and tracking"), it\'s the right size.'),
  sp(),

  h2('Phases of Microservices Design'),
  body('Designing microservices involves multiple phases, each with its own patterns:'),
  tbl(
    ['Phase','What It Decides','Patterns'],
    [
      ['Decomposition','How to split the monolith. How small is "micro"?','By Business Capability; By Subdomain (DDD)'],
      ['Database','One shared DB or separate DB per service?','Database per Service; Shared Database'],
      ['Communication','How do services talk to each other?','REST API; Event-driven; Message Queue'],
      ['Integration','How does the outside world reach your services?','API Gateway; BFF (Backend for Frontend)'],
      ['Observability','How do you monitor 50+ services?','Distributed Tracing; Centralized Logging; Metrics'],
    ],
    [1600,2800,4960]
  ),
  sp(),

  h2('Decomposition Patterns'),
  h3('Pattern 1: Decompose by Business Capability'),
  body('Split by what each piece DOES — its business function. Each business function becomes one service. Requires deep knowledge of your business domain.'),
  ...code([
    '// E-commerce platform decomposed by Business Capability:',
    'Account Service     → handles user registration, profiles, login',
    'Order Service       → handles order placement, order tracking',
    'Product Service     → handles product listings, search',
    'Inventory Service   → manages stock levels',
    'Billing Service     → generates invoices',
    'Payment Service     → processes payments',
    'Notification Service → sends emails, SMS, push notifications',
    '',
    '// Each is an independently deployable microservice.',
  ]),
  body('Challenge: You must have clarity on all your business functions upfront. If your understanding is fuzzy, you will draw the wrong service boundaries and end up with tightly-coupled services.'),
  sp(),

  h3('Pattern 2: Decompose by Subdomain (Domain-Driven Design — DDD)'),
  body('DDD takes decomposition one level deeper. A "domain" is a large functional area. Within each domain, you identify subdomains and create a microservice per subdomain.'),
  ...code([
    '// Payment is one DOMAIN — but it can have multiple services:',
    'Forward Payment Service  → handles user paying another user',
    'Refund/Reversal Service  → handles returns and refunds',
    '',
    '// Order Management is one DOMAIN:',
    'Order Placement Service  → handles placing new orders',
    'Order Tracking Service   → handles real-time order status',
    '',
    '// DDD says: first identify the domain, then find subdomains within it.',
    '// Business Capability says: find all top-level functions, each becomes a service.',
  ]),
  body('The key difference: Business Capability works top-down (find all functions). DDD works by domain boundaries first, then decomposes each domain into finer services.'),
  sp(),
  pb(),
];

// ─── VIDEO 4 — Microservices Patterns Part 2 ─────────────────────────────────
const v4 = () => [
  h1('🔧  Video 4: Microservices Design Patterns — Part 2'),
  body('Three critical patterns: Strangler Fig (how to migrate from monolith), Saga Pattern (distributed transactions), and CQRS (cross-service queries). Every senior engineer building microservices must know all three.'),
  sp(60),

  h2('Pattern 1: Strangler Fig — Safe Migration Strategy'),
  imgPara('strangler.png', 580, 264),
  sp(80),
  body('You cannot rewrite a production monolith overnight. You cannot stop traffic for 2 weeks while you rebuild everything. The Strangler Fig pattern is the safe, incremental migration approach.'),

  h3('How It Works'),
  body('Named after the Strangler Fig tree that slowly grows around an existing tree until it completely replaces it:'),
  num('Place a traffic controller (API Gateway or load balancer) in front of your monolith'),
  num('Extract ONE flow/feature from the monolith into a new microservice'),
  num('Route a small percentage of traffic (e.g., 10%) to the new microservice'),
  num('Monitor: if stable, increase traffic (10% → 25% → 50% → 100%)'),
  num('If the new microservice has bugs: instantly rollback to 0% — all traffic back to monolith'),
  num('Once 100% of that flow is on the microservice, the monolith code for it is deleted'),
  num('Repeat for every other feature until the monolith is empty and decommissioned'),
  sp(80),
  ...code([
    '// Traffic controller routing logic (pseudocode):',
    'router.handle(request):',
    '  if featureFlag("order-service-v2") and random() < 0.10:',
    '    return microservice.forward(request)  // 10% → new',
    '  else:',
    '    return monolith.forward(request)      // 90% → old',
    '',
    '// On failure in microservice:',
    'featureFlag.setRolloutPercentage("order-service-v2", 0)',
    '// All traffic immediately returns to monolith',
  ]),
  tip('The monolith is always the safety net during migration. You never lose existing functionality. You just gradually strangle it out of existence.'),
  sp(),

  h2('Database Patterns: Separate DB vs Shared DB'),
  body('Once you have decomposed your services, you face a critical database decision:'),
  tbl(
    ['Approach','What It Is','Pros','Cons'],
    [
      ['Database per Service','Each microservice has its own private database. No other service can access it directly.','Independent scaling, tech freedom, failure isolation','No cross-service JOINs; distributed transactions are complex (→ Saga)'],
      ['Shared Database','All microservices share a single database.','Easy cross-service queries; ACID transactions trivial','Tight coupling returns; one DB failure = all services down; schema changes affect all'],
    ],
    [1800,2800,2200,2560]
  ),
  sp(120),
  key('Always prefer Database per Service in production microservices. The complexity is worth it. The cross-service query and transaction problems are solved by CQRS and Saga respectively.'),
  sp(),

  h2('Pattern 2: SAGA — Distributed Transactions'),
  imgPara('saga.png', 640, 278),
  sp(80),
  body('When each service has its own database, you lose the ability to wrap multi-service operations in a single ACID transaction. The Saga pattern solves this.'),

  h3('The Core Problem'),
  body('Consider "Place an Order" which must touch 3 separate databases:'),
  ...code([
    '// Single DB (monolith) — easy:',
    'BEGIN TRANSACTION',
    '  INSERT INTO orders (order_id, user_id, items) VALUES (...)',
    '  UPDATE inventory SET stock = stock - 1 WHERE product_id = ...',
    '  INSERT INTO payments (order_id, amount) VALUES (...)',
    'COMMIT  // either all succeed or all rollback',
    '',
    '// Microservices — 3 separate databases, no shared transaction:',
    'Order Service: INSERT into orders_db   → SUCCESS',
    'Inventory Service: UPDATE inventory_db  → SUCCESS',
    'Payment Service: INSERT payments_db    → FAILS (card declined)',
    '',
    '// Problem: Order and inventory are already updated.',
    '// Payment failed. How do we undo the first two?',
  ]),

  h3('The Saga Solution: Sequence of Local Transactions'),
  body('A Saga is a sequence of local transactions. Each service performs its own local DB operation and then either publishes a success event (triggering the next step) or publishes a failure/compensation event (triggering rollbacks in reverse).'),
  ...code([
    '// Saga Happy Path (all success):',
    'Step 1: Order Service → creates order → publishes "ORDER_CREATED"',
    'Step 2: Inventory Service → hears ORDER_CREATED → deducts stock → publishes "INVENTORY_UPDATED"',
    'Step 3: Payment Service → hears INVENTORY_UPDATED → charges card → publishes "PAYMENT_SUCCESS"',
    'Step 4: Notification Service → hears PAYMENT_SUCCESS → sends confirmation email',
    '',
    '// Saga Compensation Path (payment fails):',
    'Step 3: Payment Service → charge fails → publishes "PAYMENT_FAILED"',
    'Step 2 compensation: Inventory Service → hears PAYMENT_FAILED → RESTORES stock',
    'Step 1 compensation: Order Service → hears stock restored → CANCELS order',
    '',
    '// Key: Each service does its OWN rollback using compensating transactions.',
  ]),
  sp(),

  h3('Saga Implementation: Choreography vs Orchestration'),
  tbl(
    ['Approach','How It Works','Pro','Con'],
    [
      ['Choreography','Services publish events. Other services subscribe and react autonomously. No central controller.','Fully decoupled, each service is autonomous','Risk of circular dependencies (A triggers B, B triggers C, C triggers A). Hard to visualize full flow.'],
      ['Orchestration','A central orchestrator service explicitly calls each step. Knows the full workflow.','Clear workflow, easy to trace and debug','Orchestrator becomes a new central point of failure. Some coupling to orchestrator.'],
    ],
    [1600,3200,2000,2560]
  ),
  sp(120),

  h3('Classic Interview Question on Saga'),
  body('The instructor specifically calls this out as a likely interview question:'),
  info('Question: "Person A transfers ₹10 to Person B. The balance is deducted from A\'s account successfully. But recording the payment history fails. How do you handle this?" — Answer: Saga Pattern with compensation.'),
  ...code([
    '// Services involved:',
    'Balance Service  → manages account balances (DB: balances_db)',
    'Payment Service  → records payment history (DB: payments_db)',
    '',
    '// Saga Flow:',
    'Step 1: Balance Service → deduct ₹10 from A → add ₹10 to B → publish "BALANCE_UPDATED"',
    'Step 2: Payment Service → hears BALANCE_UPDATED → record transaction → FAILS',
    '',
    '// Compensation:',
    'Payment Service → publishes "PAYMENT_RECORD_FAILED"',
    'Balance Service → hears failure → REVERSES balance (add ₹10 back to A, deduct from B)',
    '',
    '// Result: System is back to original state. ACID-like semantics achieved.',
  ]),
  sp(),

  h2('Pattern 3: CQRS — Command Query Responsibility Segregation'),
  imgPara('cqrs.png', 600, 240),
  sp(80),

  h3('The Problem: Cross-Service Joins'),
  body('With Database per Service, you cannot do a SQL JOIN across two service databases. They are separate databases, potentially on different servers or even different database technologies.'),
  ...code([
    '// This is IMPOSSIBLE with Database per Service:',
    'SELECT o.order_id, u.name, p.product_name',
    'FROM orders_db.orders o',
    'JOIN users_db.users u ON o.user_id = u.id    -- different DB!',
    'JOIN products_db.products p ON o.product_id = p.id  -- another DB!',
    '',
    '// The databases don\'t know about each other.',
  ]),

  h3('CQRS Solution: Separate Read and Write Models'),
  body('CQRS splits your data model into two:'),
  bul('Command Model (Write Side): Normalized, ACID-compliant. Handles INSERT / UPDATE / DELETE. Each service writes to its own DB.'),
  bul('Read Model (Query Side): Denormalized, pre-joined. A separate "view database" that aggregates data from multiple services for fast reads.'),
  sp(80),
  ...code([
    '// Write Side:',
    'POST /orders { userId: 1, productId: 5, qty: 2 }',
    '  → Order Command Handler → INSERT into orders_db',
    '  → Publishes "ORDER_CREATED" event',
    '',
    '// Read Side is updated by consuming events:',
    '"ORDER_CREATED" event consumed by Read Model Updater',
    '  → Queries orders_db, users_db, products_db (server-side join)',
    '  → Stores flat denormalized view in read_db:',
    '     { order_id: 1, user_name: "Alice", product_name: "Laptop", qty: 2 }',
    '',
    '// Now reads are fast:',
    'GET /orders/history?userId=1',
    '  → Query Handler → SELECT * FROM read_db WHERE user_id = 1',
    '  → Returns pre-joined data instantly, no live joins needed',
    '',
    '// Read DB can be Elasticsearch, Redis, Cassandra — whatever is fastest for queries.',
  ]),
  sp(),
  h3('Why CQRS is Powerful'),
  bul('Completely different technology for reads and writes (write to Postgres, read from Elasticsearch)'),
  bul('Read DB can be scaled independently from write DB'),
  bul('Complex reports/dashboards become simple SELECT queries'),
  bul('Audit trail: all state changes flow through commands — full history available'),
  sp(),
  pb(),
];

// ─── VIDEO 5 — Scaling 0→1M Users ────────────────────────────────────────────
const v5 = () => [
  h1('🚀  Video 5: Scaling from 0 to 1 Million Users'),
  body('This is the most comprehensive system design video in the series. It covers the full evolution of a system architecture across nine steps — from a single server handling zero users to a distributed platform handling millions of concurrent requests.'),
  imgPara('scale.png', 700, 305),
  sp(80),

  h2('Step 1 — Single Server (0 users)'),
  body('Starting point: everything runs on one machine. Application code AND the database live on the same server. The client (web browser or mobile app) talks directly to this server.'),
  ...code([
    'Architecture:',
    '  Client (Web/Mobile)',
    '      ↓',
    '  Single Server',
    '  ├── Application Code (business logic)',
    '  └── Database (MySQL/PostgreSQL)',
    '',
    '// Problems:',
    '// - DB and app compete for same CPU/RAM',
    '// - Can\'t scale them independently',
    '// - Single point of failure',
  ]),
  sp(),

  h2('Step 2 — Separate Application Server and Database Server'),
  body('The first scaling move: put the app and database on separate machines. This is the mid-tier / data-tier split.'),
  bul('Mid-Tier (Application Server): Runs only business logic. Can scale horizontally.'),
  bul('Data-Tier (DB Server): Runs only the database. Can have different hardware (more RAM, faster disks).'),
  bul('Now they can grow independently — add more CPU to the app server without touching the DB, and vice versa'),
  sp(),

  h2('Step 3 — Load Balancer + Multiple App Servers'),
  imgPara('db_replication.png', 520, 240),
  sp(80),
  body('One app server has a ceiling — it can handle maybe 1,000 requests per minute. Beyond that, it starts dropping requests. Solution: add more app servers and put a load balancer in front.'),

  h3('What the Load Balancer Does'),
  bul('Receives all incoming traffic — it\'s the only public IP your clients need to know'),
  bul('Distributes requests across healthy app servers (Round Robin, Least Connections, etc.)'),
  bul('App servers use private IPs — clients on the internet cannot reach them directly'),
  bul('Health checks: if an app server dies, the LB stops sending traffic to it automatically'),
  ...code([
    'Architecture:',
    '  Client',
    '      ↓',
    '  Load Balancer (public IP: 203.0.113.1)',
    '  ├── App Server 1 (private IP: 10.0.0.1)',
    '  ├── App Server 2 (private IP: 10.0.0.2)',
    '  └── App Server 3 (private IP: 10.0.0.3)',
    '              ↓',
    '         DB Server',
    '',
    '// Security benefit: no attacker can directly reach app servers.',
    '// They can only reach the load balancer.',
  ]),
  sp(),

  h2('Step 4 — Database Replication (Master–Slave)'),
  body('The database is now the bottleneck. One DB server is a single point of failure AND limits read performance. Solution: database replication.'),
  bul('Master DB — accepts all WRITE operations (INSERT, UPDATE, DELETE). One master.'),
  bul('Slave DB(s) — accept all READ operations (SELECT). Can have multiple slaves.'),
  bul('Master continuously replicates changes to all slaves'),
  bul('If Master fails: a slave is promoted to master automatically'),
  bul('If a slave fails: other slaves handle reads; master temporarily takes reads'),
  ...code([
    '// Traffic routing:',
    'Write requests → Master DB only',
    'Read requests  → Slave DB 1, Slave DB 2 (load balanced)',
    '',
    '// Why this is powerful:',
    '// In most apps, reads outnumber writes 80-90% of the time.',
    '// You can have 5 slave read replicas to handle read load.',
    '// One master handles all writes (typically much lower volume).',
  ]),
  sp(),

  h2('Step 5 — Caching (Redis / Memcached)'),
  body('Database queries are expensive — they go to disk, run query plans, network hops. If the same data is read thousands of times, why hit the DB every time? Cache it in memory.'),
  h3('Cache-Aside Pattern (Most Common)'),
  ...code([
    'def get_user_profile(user_id):',
    '    # 1. Check cache first',
    '    cached = redis.get(f"user:{user_id}")',
    '    if cached:',
    '        return cached  # Cache HIT — microseconds ⚡',
    '',
    '    # 2. Cache MISS — go to database',
    '    user = db.query("SELECT * FROM users WHERE id = ?", user_id)',
    '',
    '    # 3. Store in cache with TTL',
    '    redis.setex(f"user:{user_id}", ttl=3600, value=user)  # 1 hour TTL',
    '',
    '    return user  # next request will be a cache hit',
    '',
    '# TTL (Time To Live): data auto-expires after set duration.',
    '# Prevents serving stale data forever.',
    '# After TTL, cache is refreshed from DB on next request.',
  ]),
  bul('Cache Hit: data served from memory in microseconds (100-500x faster than DB)'),
  bul('Cache Miss: go to DB, store result in cache, return to user'),
  bul('TTL: set based on how frequently data changes (session data: minutes; product catalog: hours; config: days)'),
  sp(),

  h2('Step 6 — CDN (Content Delivery Network)'),
  imgPara('cdn.png', 600, 347),
  sp(80),
  body('Your server is in India. A user in Japan requests your website. Every request travels India→Japan→India — adding hundreds of milliseconds of latency. CDN solves this by caching content at the edge, near your users.'),

  h3('CDN vs Regular Cache — Key Distinction'),
  body('The instructor makes this point explicitly: "CDN does caching — but not everything that does caching is CDN." A CDN is NOT just a cache:'),
  tbl(
    ['','Regular Cache (e.g., Redis)','CDN'],
    [
      ['Location','Same data center as your servers','Hundreds of edge locations globally'],
      ['What it caches','Dynamic data (DB query results)','Static assets (HTML, CSS, JS, images, videos)'],
      ['Primary benefit','Reduces database load','Reduces latency for global users'],
      ['Security features','Not built-in','DDoS protection, bot detection, WAF'],
    ],
    [1400,3400,4560]
  ),
  sp(120),

  h3('How CDN Works'),
  num('User in Japan requests your website'),
  num('DNS resolves to nearest CDN edge node in Japan'),
  num('Edge node checks its cache → HIT: serve immediately (< 10ms)'),
  num('Cache MISS → ask neighboring CDN region'),
  num('If no one has it → CDN fetches from your origin server and caches locally'),
  num('Future requests for the same content in Japan are served from the Japan edge'),
  sp(80),
  tip('CDN adds security: DDoS attacks hit the CDN edge, not your origin servers. Smart CDNs can detect and filter bot traffic before it reaches you.'),
  sp(),

  h2('Step 7 — Multiple Data Centers'),
  body('A single data center is a geographic single point of failure. If your Mumbai data center loses power, all global users are affected. Solution: replicate your entire setup across multiple regions.'),
  bul('Active-Active: both data centers serve traffic simultaneously. Load balancer routes by geography.'),
  bul('Active-Passive: one DC is primary, the other is a hot standby (failover).'),
  bul('DB replication happens cross-region — changes in Mumbai replicate to Singapore.'),
  bul('Global Load Balancer: routes users to the closest data center with < threshold latency.'),
  ...code([
    '// Global Load Balancer routing:',
    'if user.region == "APAC":',
    '    route_to(singapore_datacenter)',
    'elif user.region == "US":',
    '    route_to(virginia_datacenter)',
    'elif singapore_datacenter.is_down():',
    '    route_to(virginia_datacenter)  // automatic failover',
  ]),
  sp(),

  h2('Step 8 — Message Queue (Asynchronous Processing)'),
  imgPara('mq.png', 640, 278),
  sp(80),
  body('Some operations are slow but don\'t need to happen before you respond to the user. Sending a confirmation email after an order, for example, doesn\'t need to block the "order placed" response. Message queues decouple producers from consumers, enabling async processing.'),

  h3('The Core Value'),
  ...code([
    '// WITHOUT message queue (synchronous — slow):',
    'POST /place-order',
    '  1. Save order to DB           (5ms)',
    '  2. Send confirmation email    (300ms) ← user waiting!',
    '  3. Update inventory           (10ms)',
    '  4. Send push notification     (200ms) ← still waiting!',
    'Response returned after: ~515ms',
    '',
    '// WITH message queue (async — fast):',
    'POST /place-order',
    '  1. Save order to DB           (5ms)',
    '  2. Publish "ORDER_PLACED" event to queue  (2ms)',
    'Response returned after: ~7ms  ← user gets instant response',
    '',
    '// Meanwhile (asynchronously):',
    'Email Worker → reads "ORDER_PLACED" → sends email (300ms)',
    'Inventory Worker → reads "ORDER_PLACED" → updates stock (10ms)',
    'Push Worker → reads "ORDER_PLACED" → sends notification (200ms)',
    '// All happen in parallel without blocking the user',
  ]),

  h3('RabbitMQ Internals — Key Terminology'),
  tbl(
    ['Component','Role'],
    [
      ['Producer','The service that creates and sends messages to the queue'],
      ['Exchange','Receives messages from producers; routes them to queues based on rules'],
      ['Binding','The rule/link between an Exchange and a Queue. Uses a "binding key".'],
      ['Queue','Buffer that holds messages until a consumer processes them'],
      ['Consumer/Subscriber','The service that reads messages from a queue and processes them'],
      ['Routing Key','Metadata attached to a message; Exchange uses it to decide which Queue to send to'],
    ],
    [1800,7560]
  ),
  sp(120),

  h3('Exchange Types'),
  bul('Direct: routing key must exactly match the binding key of a queue. One-to-one routing.'),
  bul('Fanout: message is sent to ALL queues bound to the exchange. Broadcast.'),
  bul('Topic: wildcard matching. routing key "order.*.created" matches multiple queues. Flexible many-to-many routing.'),
  ...code([
    '// Direct Exchange:',
    'producer.publish(routing_key="email", message=order_data)',
    '// Exchange: routing_key "email" matches Queue1\'s binding key "email"',
    '// → Only Queue1 receives it → only Email Worker processes it',
    '',
    '// Fanout Exchange:',
    'producer.publish(exchange="order_events", message=order_data)',
    '// All queues bound to "order_events" receive the message',
    '// → Email Worker, SMS Worker, Analytics Worker all receive it',
    '',
    '// Topic Exchange:',
    'producer.publish(routing_key="order.us.created", message=order_data)',
    '// Queue with binding "order.*.created" matches',
    '// Queue with binding "order.us.*" also matches',
    '// → Multiple queues can receive based on wildcard rules',
  ]),
  sp(),

  h2('Step 9 — Database Scaling'),
  h3('Vertical Scaling (Scale Up)'),
  body('Add more resources to the existing database server — more CPU, more RAM, faster NVMe disks. Simple, but has hard limits. At some point you simply can\'t add more CPU or RAM. You\'ve hit the hardware ceiling.'),

  h3('Horizontal Scaling (Scale Out) — Sharding'),
  imgPara('sharding.png', 600, 320),
  sp(80),
  body('Add more database nodes by splitting data across them. This is called sharding.'),

  h3('Horizontal Sharding (Row-wise Partitioning)'),
  body('Split your table rows across multiple databases based on a sharding key.'),
  ...code([
    '// Users table with 10 million rows, sharded by name:',
    'Shard 1 (DB_1): rows where name starts A–M  (≈5M rows)',
    'Shard 2 (DB_2): rows where name starts N–Z  (≈5M rows)',
    '',
    '// Routing logic:',
    'def get_shard(name: str) → DB:',
    '    if name[0].upper() <= "M":',
    '        return db_shard_1',
    '    return db_shard_2',
    '',
    '// Query for "Alice" → goes to Shard 1',
    '// Query for "Rahul" → goes to Shard 2',
  ]),

  h3('Vertical Sharding (Column-wise Partitioning)'),
  body('Split a wide table into multiple narrower tables, each with all rows but only some columns.'),
  ...code([
    '// Original users table (10 columns):',
    'users(id, name, email, phone, address, last_login, preferences, orders_count, ...)',
    '',
    '// Split into hot (frequent access) and cold (rare access):',
    'users_hot(id, name, email)                           -- DB_1: fast SSD',
    'users_cold(id, phone, address, preferences, ...)    -- DB_2: cheap HDD',
    '',
    '// Read profile page? Only hit users_hot.',
    '// Read full account settings? Join both.',
  ]),

  h3('Sharding Drawbacks — Important for Interviews'),
  tbl(
    ['Problem','What Happens','Solution'],
    [
      ['Hot shards','If many users\' names start with S, Shard 1 fills up while Shard 2 is empty. Uneven load.','Consistent Hashing for even distribution'],
      ['Re-sharding complexity','Adding a new shard means moving millions of records to rebalance.','Consistent Hashing minimizes data movement to 1/N'],
      ['No cross-shard JOINs','User data is in Shard 1, their orders in Shard 2. No direct JOIN possible.','Denormalization — store needed data in each shard'],
    ],
    [1800,3200,4360]
  ),
  sp(120),
  info('The instructor explicitly mentions: "Consistent Hashing solves the resharding problem." This leads naturally into the next topic in the series.'),
  sp(),
  imgPara('consistent_hash.png', 440, 440),
  sp(80),

  h3('Consistent Hashing — Preview'),
  body('Normal sharding uses modulo arithmetic. If you have 3 shards and add a 4th, the modulo changes from mod-3 to mod-4 — meaning every single key now maps to a different shard. This requires rebalancing ALL data.'),
  body('Consistent Hashing places servers on a virtual ring. When a new server is added, only the keys between the new server and its predecessor need to move — approximately 1/N of total keys, where N is the number of nodes.'),
  ...code([
    '// Normal hashing — adding a shard breaks everything:',
    'hash("Alice") % 3 = 1  → Shard 1',
    'hash("Alice") % 4 = 3  → Shard 3  (different! must rebalance)',
    '',
    '// Consistent hashing — adding a shard is minimal:',
    '// Keys already on Shard 1, 2, 3 are UNAFFECTED.',
    '// Only keys that "cross" the new server\'s position on the ring move.',
    '// ~1/N of total keys need rebalancing.',
  ]),
  sp(),

  h2('Full Architecture — Step 9 Summary'),
  tbl(
    ['Layer','Component','Role'],
    [
      ['Edge','CDN','Cache static assets globally, DDoS protection'],
      ['Entry','Global Load Balancer','Geo-routing, multi-datacenter failover'],
      ['App','Multiple App Servers + Load Balancer','Horizontal scaling, fault tolerance'],
      ['Cache','Redis/Memcached','In-memory cache, reduce DB calls'],
      ['Async','Message Queue (Kafka/RabbitMQ)','Async processing, decouple services'],
      ['Data','Master DB + Slave Replicas','Write/read separation, replication'],
      ['Scale','Sharded DBs + Consistent Hashing','Horizontal data scaling'],
      ['Global','Multiple Data Centers','Geographic redundancy, low latency'],
    ],
    [1200,2400,5760]
  ),
  sp(),
  pb(),
];

// ─── Quick Reference / Cheat Sheet ───────────────────────────────────────────
const cheatSheet = () => [
  h1('⚡  HLD Quick Reference Cheat Sheet'),
  sp(60),

  h2('Protocol Decision Matrix'),
  tbl(
    ['If you\'re designing...','Use this protocol','Why'],
    [
      ['REST API / website','HTTP/HTTPS','Standard stateless request-response'],
      ['Chat app (WhatsApp, Telegram)','WebSocket','Bidirectional — server pushes messages to clients'],
      ['Video calling (Google Meet, Zoom)','WebRTC + UDP','P2P, low-latency, packet loss tolerable'],
      ['Live video streaming','UDP (+ RTMP/HLS)','Speed over reliability; skip dropped frames'],
      ['File transfer (secure)','HTTPS/SFTP','Encrypted, reliable, ordered delivery'],
      ['Email sending','SMTP','Standard send protocol'],
      ['Email reading (multiple devices)','IMAP','Server-side storage, multi-device sync'],
    ],
    [2600,2000,4760]
  ),
  sp(),

  h2('CAP Theorem Quick Picks'),
  tbl(
    ['System','Choose','Because'],
    [
      ['Banking / Payments / Finance','CP','Stale balance data = money lost. Prefer brief unavailability.'],
      ['Social Media (likes, feeds)','AP','Seeing slightly stale like count is fine. App going down is not.'],
      ['Shopping Cart','AP','Cart showing old items briefly is acceptable.'],
      ['Distributed Lock (ZooKeeper)','CP','Wrong lock = race condition = data corruption.'],
      ['Product Catalogue / Search','AP','Slightly stale product info is fine.'],
      ['Stock Trading Platform','CP','Price accuracy is non-negotiable.'],
    ],
    [2400,1200,5760]
  ),
  sp(),

  h2('Microservices Pattern Selector'),
  tbl(
    ['Situation','Use This Pattern'],
    [
      ['Migrating monolith to microservices safely','Strangler Fig — gradual traffic shifting with instant rollback'],
      ['Distributed transaction across multiple DBs','SAGA Pattern (choreography or orchestration)'],
      ['Need to JOIN data across service databases','CQRS — separate denormalized read model'],
      ['How small should each microservice be?','Decompose by Business Capability or by Subdomain (DDD)'],
      ['Each service needs its own DB tech','Database per Service pattern'],
    ],
    [3000,6360]
  ),
  sp(),

  h2('Scaling Checklist — 0 → 1M Users'),
  tbl(
    ['Users','Add This','Why'],
    [
      ['0–1K','Single server (App + DB together)','Keep it simple, no overhead needed'],
      ['1K–10K','Separate App Server + DB Server','Independent scaling, remove coupling'],
      ['10K–100K','Load Balancer + multiple App Servers','Handle more concurrent requests'],
      ['100K–500K','DB Replication (Master + Slaves) + Caching','Read scaling + fast in-memory responses'],
      ['500K–1M','CDN + Multiple Data Centers','Global latency reduction + geo-redundancy'],
      ['1M+','Message Queue + DB Sharding + Consistent Hashing','Async processing + data partitioning'],
    ],
    [1200,2800,5360]
  ),
  sp(),
];

// ─── Main build function ──────────────────────────────────────────────────────
async function main() {
  const allChildren = [
    ...cover(),
    ...v1(),
    ...v2(),
    ...v3(),
    ...v4(),
    ...v5(),
    ...cheatSheet(),
  ];

  const doc = new Document({
    numbering: {
      config: [
        { reference:'bullets', levels:[
          { level:0, format:LevelFormat.BULLET, text:'•', alignment:AlignmentType.LEFT,
            style:{paragraph:{indent:{left:720,hanging:360}}} },
          { level:1, format:LevelFormat.BULLET, text:'○', alignment:AlignmentType.LEFT,
            style:{paragraph:{indent:{left:1080,hanging:360}}} },
        ]},
        { reference:'numbers', levels:[
          { level:0, format:LevelFormat.DECIMAL, text:'%1.', alignment:AlignmentType.LEFT,
            style:{paragraph:{indent:{left:720,hanging:360}}} },
        ]},
      ]
    },
    styles: {
      default: { document:{ run:{ font:'Calibri', size:22 } } },
      paragraphStyles: [
        { id:'Heading1', name:'Heading 1', basedOn:'Normal', next:'Normal', quickFormat:true,
          run:{ size:44, bold:true, font:'Calibri', color:P.navy },
          paragraph:{ spacing:{before:440,after:180}, outlineLevel:0 } },
        { id:'Heading2', name:'Heading 2', basedOn:'Normal', next:'Normal', quickFormat:true,
          run:{ size:34, bold:true, font:'Calibri', color:P.blue },
          paragraph:{ spacing:{before:320,after:120}, outlineLevel:1 } },
        { id:'Heading3', name:'Heading 3', basedOn:'Normal', next:'Normal', quickFormat:true,
          run:{ size:26, bold:true, font:'Calibri', color:P.teal },
          paragraph:{ spacing:{before:220,after:80}, outlineLevel:2 } },
      ]
    },
    sections:[{
      properties: {
        page: {
          size:{ width:12240, height:15840 },
          margin:{ top:1080, right:1080, bottom:1080, left:1080 }
        }
      },
      headers: {
        default: new Header({ children:[
          new Paragraph({
            border:{ bottom:{ style:BorderStyle.SINGLE, size:4, color:'E2E8F0', space:1 } },
            children:[
              new TextRun({ text:'HLD Series — Concept Coding  |  Set 1', size:18, font:'Calibri', color:'94A3B8', italics:true }),
            ]
          })
        ]})
      },
      footers: {
        default: new Footer({ children:[
          new Paragraph({
            border:{ top:{ style:BorderStyle.SINGLE, size:4, color:'E2E8F0', space:1 } },
            children:[
              new TextRun({ text:'Covers: Network Protocols  •  CAP Theorem  •  Monolith vs Microservices  •  Microservices Patterns  •  Scaling 0→1M', size:16, font:'Calibri', color:'94A3B8' }),
            ]
          })
        ]})
      },
      children: allChildren
    }]
  });

  const buf = await Packer.toBuffer(doc);
  fs.writeFileSync('/home/claude/HLD_Notes_Set1.docx', buf);
  console.log('Done! Size:', buf.length, 'bytes');
}

main().catch(e=>{ console.error(e); process.exit(1); });