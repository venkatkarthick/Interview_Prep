Data Migration Process
Data migration projects can be stressful since they involve moving critical data and often impact other stakeholders. You’ve heard horror stories of extended system downtime and lost or corrupted data. Which is why you need a well-defined data migration plan before you start moving anything.

Your needs are unique, so not all of the steps described in this section may be necessary for your migration. Plus, this guidance assumes you’re executing the full migration as a one-time event in a limited time window. However, you may choose to execute a more complex, phased approach where both the old and new systems work in parallel.

Below we describe the three key phases of the data migration process for most projects: PLAN, EXECUTE and VERIFY.

A flowchart with three phases: Plan (access source data, design migration, brief stakeholders, build solution, test solution), Execute (implement solution), and Verify (validate migration, decommission old system).

1. PLAN
   This first phase is the most critical. Here you’ll assess and clean your source data, analyze business requirements and dependencies, develop and test your migration scenarios, and codify a formal data migration plan.

Assess and clean your source data. The first step in your planning phase is to gain a full understanding of the size, stability and format of the data you plan to move. This means performing a complete audit on your source data, looking for inaccurate or incomplete data fields. This audit will also help you in mapping fields to your target system and identifying gaps. After the audit is complete, you’ll obviously need to clean the data, resolving any issues you uncovered.

Design your migration. Now you’re ready to specify what data will be moved, the details of your project’s technical architecture and processes, plus budget and timelines. Key aspects of this design will be identifying tools and resources needed, setting tight security standards and establishing data quality controls.

Brief your key stakeholders. With your migration design in hand, you should communicate to all relevant stakeholders the goals of the project, key milestones and any elements which may impact other teams, such as system downtime.

Build your solution. Finally, you can start coding the migration logic you designed to extract, transform and load data into the new repository. If you’re working with a large dataset, you can consider splitting it into sections and then build and test, section by section.

Test your solution. Even though you tested repeatedly during the build out, you should definitely test your completed code with a mirror of the production environment before moving to the execute phase.


2. EXECUTE
   Implement your solution. The time has come to execute the migration. This can be a stressful time, especially for stakeholders who are directly impacted, but you’ve thoroughly tested your solution and can now proceed.


3. VERIFY
   Validate your migration. To ensure your migration went as planned, you should conduct data validation testing. Specifically, you’ll want to check if all the required data was transferred, if there are correct values in the destination tables and if there was any data loss.

Decommission your old systems. The final step in the migration process is to shut down and dispose of the legacy systems which supported your source data. This will result in cost savings and resource efficiencies.